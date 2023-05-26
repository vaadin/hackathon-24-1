package com.example.application.views.grids;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.grid.ColumnRendering;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Route(value = "lazy", layout = MainLayout.class)
public class LazyLoadingView extends VerticalLayout {

    private static final int COLUMNS = 100;

    public LazyLoadingView() {
        var grid = new Grid<Data>();

        grid.setItems(DataProvider.fromCallbacks(query -> {
            int offset = query.getOffset();
            int limit = query.getLimit();

            Stream<Data> stream = IntStream.range(0, limit).mapToObj(index -> new Data(offset + index));

            Comparator<Data> memorySorting = query.getInMemorySorting();
            if (memorySorting != null) {
                stream = stream.sorted(memorySorting);
            }

            return stream;
        }, query -> 1000));

        grid.setColumnRendering(ColumnRendering.LAZY);

        IntStream.range(0, COLUMNS).forEach(i -> {
            grid.addColumn(data -> data.getValue(i))
                    .setHeader("Column " + (i + 1))
                    .setSortable(true)
                    .setResizable(true)
                    .setAutoWidth(true);
        });

        addAndExpand(grid);
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private static class Data {
        private final long id;
        private final List<String> values;

        private Data(long id) {
            this.id = id;

            values = new ArrayList<>();

            for (int i = 0; i < COLUMNS; i++) {
                values.add("Value " + id + "." + (i + 1));
            }
        }

        public String getValue(int index) {
            return index >= values.size() ? "n/a" : values.get(index);
        }

        public long getId() {
            return id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Data data = (Data) o;
            return id == data.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

}
