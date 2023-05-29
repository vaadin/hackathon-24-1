package com.summary.application.views.summary;

import com.summary.application.service.CallPythonScriptForYoutubeCaption;
import com.summary.application.service.OpenAI;
import com.summary.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Summary")
@Route(value = "main", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@AnonymousAllowed
@Uses(Icon.class)
public class SummaryView extends Div {

    private TextField youtubeVideoUrl = new TextField("Youtube Video URL");
    private TextArea summaryTextArea = new TextArea("Summary of the video");
    private Button captionBtn = new Button("Get Caption");
    private Button summaryBtn = new Button("Get Summary");
    private String caption = "";

    public SummaryView() {
        addClassName("summary-view");

        NativeLabel example = new NativeLabel("Example: https://www.youtube.com/watch?v=bKUmbMDvnuU");

        youtubeVideoUrl.setWidth("400px");

        HorizontalLayout firstLine = new HorizontalLayout();
        HorizontalLayout secondLine = new HorizontalLayout();
        HorizontalLayout thirdLine = new HorizontalLayout();

        firstLine.add(youtubeVideoUrl);
        secondLine.add(captionBtn);
        secondLine.add(summaryBtn);
        summaryBtn.setEnabled(false);

        thirdLine.add(summaryTextArea);
        summaryTextArea.setWidth("400px");
        summaryTextArea.setHeight("400px");
        summaryTextArea.setVisible(false);

        add(example);
        add(createTitle());
        add(firstLine);
        add(secondLine);
        add(thirdLine);

        captionBtn.addClickListener(e -> {
            String videoUrl = youtubeVideoUrl.getValue();
            String videoId = youtubeVideoUrl.getValue().substring(videoUrl.indexOf("=") + 1);
            System.out.println("VideoId: " + videoId);
            caption = CallPythonScriptForYoutubeCaption.getCaptionFromPythonScript(videoId);
            Notification.show(caption);
            summaryBtn.setEnabled(true);
        });

        summaryBtn.addClickListener(e -> {
            String summary = OpenAI.getSummary(caption);
//            Notification.show(summary);
            summaryTextArea.setValue(summary);
            summaryTextArea.setVisible(true);
        });
    }

    private Component createTitle() {
        return new H3("Youtube summary generation.");
    }
}
