package twittrfx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import twittrfx.models.BirdPM;
import twittrfx.utils.FileHandler;

public class PresentationModel {

  private final StringProperty applicationTitle = new SimpleStringProperty("JavaFX App");
  private final StringProperty greeting = new SimpleStringProperty("Hello World!");

  public final static ObservableList<BirdPM> birds = FXCollections.observableArrayList();
  
  public PresentationModel() {
    birds.addAll(new FileHandler().readFromFile());
  }

  public String getApplicationTitle() {
    return applicationTitle.get();
  }

  public StringProperty applicationTitleProperty() {
    return applicationTitle;
  }

  public void setApplicationTitle(String applicationTitle) {
    this.applicationTitle.set(applicationTitle);
  }

  public String getGreeting() {
    return greeting.get();
  }

  public StringProperty greetingProperty() {
    return greeting;
  }

  public void setGreeting(String greeting) {
    this.greeting.set(greeting);
  }

  public ObservableList<BirdPM> getBirds() {
    return birds;
  }

  public Integer highestTopSpeed() {
    return getBirds()
      .stream()
      .mapToInt(bird -> bird.getTopSpeedInKmh())
      .max()
      .orElse(0);
  }

  public Integer amountOfBirdSpecies() {
    return getBirds().size();
  }
}
