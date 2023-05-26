package com.vaadin.pwademo.tasks

import com.github.mvysny.karibudsl.v10.*
import com.github.mvysny.kaributools.refresh
import com.github.mvysny.vokdataloader.asc
import com.github.mvysny.vokdataloader.desc
import com.github.mvysny.vokdataloader.sortedBy
import com.github.vokorm.dataloader.dataLoader
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.checkbox.Checkbox
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.icon.Icon
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.renderer.ComponentRenderer
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import com.vaadin.pwademo.MainLayout
import eu.vaadinonkotlin.vaadin.BooleanComboBox
import eu.vaadinonkotlin.vaadin.VokFilterBar
import eu.vaadinonkotlin.vaadin.asFilterBar
import eu.vaadinonkotlin.vaadin.istartsWith
import eu.vaadinonkotlin.vaadin.vokdb.setDataLoader

/**
 * The main view of the app. It is a vertical layout which lays out the child components vertically. There are only two components:
 *
 * * a custom component named [AddTaskForm] which is a form used to add new items to the task list;
 * * a Vaadin Grid which lists all current tasks
 *
 * The UI is defined by the means of so-called DSL; see [Karibu-DSL examples](https://github.com/mvysny/karibu-dsl#how-to-write-dsls-for-vaadin-8-and-vaadin8-v7-compat)
 * for more examples.
 *
 * Note that the `@Route` annotation defines the main layout with which this view is wrapped in. See [MainLayout] for details on how to
 * create an app-wide layout which hosts views.
 */
@Route("", layout = MainLayout::class)
@PageTitle("Task List")
class TaskListView : KComposite() {
    private lateinit var form: AddTaskForm
    private lateinit var grid: Grid<Task>
    private val root = ui {
        verticalLayout {
            setSizeFull(); isPadding = false

            verticalLayout { // add a bit of padding
                form = addTaskForm {
                    onAddTask = { task ->
                        task.save()
                        grid.refresh()
                    }
                }
            }

            grid = grid<Task> {
                width = "100%"; isExpand = true

                setDataLoader(Task.dataLoader.sortedBy(Task::completed.asc, Task::created.desc))

                appendHeaderRow() // workaround for https://github.com/vaadin/vaadin-grid-flow/issues/912
                val filterBar: VokFilterBar<Task> = appendHeaderRow().asFilterBar(this)

                columnFor(Task::completed, createTaskCompletedCheckboxRenderer()) {
                    isExpand = false; setHeader("Done"); width = "130px"
                    filterBar.forField(BooleanComboBox(), this).eq()
                }
                columnFor(Task::title, createTaskNameDivRenderer()) {
                    filterBar.forField(TextField(), this).istartsWith()
                }
                addColumn(newDeleteButtonRenderer()).apply {
                    isExpand = false; width = "90px"
                }
            }
        }
    }

    private fun createTaskCompletedCheckboxRenderer(): ComponentRenderer<Checkbox, Task> =
        ComponentRenderer { task ->
            Checkbox(task.completed).apply {
                // when the check box is changed, update the task and reload the grid
                addValueChangeListener {
                    task.completed = it.value
                    task.save()
                    grid.dataProvider.refreshAll()
                }
            }
        }

    private fun createTaskNameDivRenderer(): ComponentRenderer<Div, Task> =
        ComponentRenderer { task ->
            Div().apply {
                text = task.title
                if (task.completed) {
                    classNames.add("crossedout")
                }
            }
        }

    private fun newDeleteButtonRenderer(): ComponentRenderer<Button, Task> =
        ComponentRenderer { task ->
            Button(Icon(VaadinIcon.TRASH)).apply {
                addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE)  // this will remove the button border
                onLeftClick {
                    task.delete()
                    grid.dataProvider.refreshAll()
                }
            }
        }
}