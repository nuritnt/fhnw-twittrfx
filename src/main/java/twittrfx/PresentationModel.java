package twittrfx;

import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import twittrfx.models.BirdPM;
import twittrfx.utils.FileHandler;

public class PresentationModel {

  private final StringProperty applicationTitle = new SimpleStringProperty(Caption.APLLICATION_TITLE.get(Language.EN));
  private final StringProperty greeting = new SimpleStringProperty("Hello World!");  
  public final static ObservableList<BirdPM> birds = FXCollections.observableArrayList();
  private ObjectProperty<BirdPM> selectedBird = new SimpleObjectProperty<>();

  private BooleanProperty creatingNewBird = new SimpleBooleanProperty(false);

  public enum Language {
    DE("Deutsch"),
    EN("English");

    private final String language;

    Language(String language) {
      this.language = language;
    }

    public String getLanguage() {
      return language;
    }
  }

  public enum Caption {
    APLLICATION_TITLE("TwittrFX", "TwittrFX"),
    BIRDS_OF_SWITZERLAND("Birds of Switzerland", "Vögel der Schweiz"),
    AMOUNT_OF_BIRDS("Amount of Birds", "Anzahl der Vögel"),
    HIGHEST_TOP_SPEED("Highest Top Speed", "Höchste Spitzengeschwindigkeit"),
    
    NAME("Name", "Name"),
    SHORT_DESCRIPTION("Short Description", "Kurzbeschreibung"),
    POPULATION_SIZE("Population Size", "Bevölkerungsgrösse"),
    TOP_SPEED("Top Speed", "Höchstgeschwindigkeit"),
    MAXIMUM_AGE("Maximum Life Span", "Maximale Lebensdauer"),
    LENGTH("Length", "Länge"),
    WEIGHT("Weight", "Gewicht"),
    WINGSPAN("Wingspan", "Spannweite"),
    CONTINENTS("Continents", "Kontinente"),
    INCUBATION_PERIOD("Incubation Period", "Brutzeit"),
    DIET("Diet", "Diät"),
    SEASONAL_BEHAVIOR("Seasonal Behavior", "Saisonales Verhalten"),
    INDEPENDENT_AGE("Independent Age", "Selbstständiges Alter"),
    POPULATION_TREND("Population Trend", "Populationstrend"),
    POPULATION_STATUS("Population Status", "Bevölkerungsstatus"),
    IMAGE("Image", "Bild");

    private final String english;
    private final String german;

    Caption(String english, String german) {
      this.english = english;
      this.german = german;
    }

    public String getEnglish() {
      return english;
    }

    public String getGerman() {
      return german;
    }

    public String get(Language language) {
      switch (language) {
        case DE:
          return getGerman();
        case EN:
          return getEnglish();
        default:
          return getEnglish();
      }
    }
  }

  public PresentationModel(Language language) {
    birds.addAll(new FileHandler().readFromFile());
    
    if (!birds.isEmpty()) {
        selectedBird.setValue(birds.get(0));
    }
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
    setCreatingNewBird(true);
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

  private ObjectProperty<Language> currentLanguage = new SimpleObjectProperty<>(Language.EN); // Default to English

  public ObjectProperty<Language> languageProperty() {
    return currentLanguage;
  }

  public final Language getLanguage() {
    return currentLanguage.get();
  }

  public void setLanguage(Language lang) {
      this.currentLanguage.set(lang);
  }

  public String getCaption(Caption caption) {
    return caption.get(currentLanguage.get());
  }

  public boolean isCreatingNewBird() {
      return creatingNewBird.get();
  }

  public void setCreatingNewBird(boolean creatingNewBird) {
      this.creatingNewBird.set(creatingNewBird);
  }

  public BooleanProperty creatingNewBirdProperty() {
    return creatingNewBird;
  }
}
