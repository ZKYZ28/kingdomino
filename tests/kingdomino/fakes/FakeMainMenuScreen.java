package kingdomino.fakes;

import java.util.List;

import kingdomino.supervisers.MainMenuView;

public class FakeMainMenuScreen extends Fake implements MainMenuView {

	@Override
	public String getName() {
		super.countCall("getName");
		return "MainMenu";
	}

	@Override
	public void goTo(String screenName) {
		super.countCall("goTo", screenName);
	}

	@Override
	public void onEnter(String fromScreen) {
		super.countCall("onEnter", fromScreen);
	}

	@Override
	public void onLeave(String toScreen) {
		super.countCall("onLeave", toScreen);
	}

	@Override
	public void setItems(List<String> items) {
		super.countCall("setItems", items);
	}

	@Override
	public void onQuitConfirmed(String fromScreen) {
		super.countCall("onQuitConfirmed", fromScreen);		
	}

}
