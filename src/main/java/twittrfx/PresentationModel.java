package twittrfx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
  private ObjectProperty<BirdPM> selectedBird = new SimpleObjectProperty<>();

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
  
  public BirdPM getSelectedBird() {
    return selectedBird.getValue();
  }
  
  public void setSelectedBird(BirdPM selectedBird) {
    this.selectedBird.setValue(selectedBird);
  }
  
  public ObjectProperty<BirdPM> selectedBirdProperty() {
      return selectedBird;
  }

  public void addBird() {
    BirdPM newBird = new BirdPM("New 🐓");
    birds.add(newBird);
    selectedBird.setValue(newBird);
  }

  public void deleteBird() {
    birds.remove(selectedBird.getValue());
    selectedBird.setValue(null);
  }

  public void save() {
    FileHandler fileHandler = new FileHandler();
    try {
        fileHandler.save(birds);
    } catch (Exception e) {
        e.printStackTrace();
        throw new IllegalStateException("save failed");
    }
  }
}
