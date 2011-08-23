package ch.creasystem.tennis.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class PlayerWidget extends Composite{ // Could extend Widget instead
	interface MyUiBinder extends UiBinder<Widget, PlayerWidget> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@UiField
	SpanElement nameSpan;

	public PlayerWidget() {
		// createAndBindUi initializes this.nameSpan
		initWidget(uiBinder.createAndBindUi(this));
		setName("Dave's Widget");

	}

	public void setName(String name) {
		nameSpan.setInnerText(name);
	}
}