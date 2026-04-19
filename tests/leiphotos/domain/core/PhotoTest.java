package leiphotos.domain.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import leiphotos.domain.facade.GPSCoordinates;

class PhotoTest {

  @Test
  void testCreatePhotoWithoutGPS() {
    LocalDateTime expectedCapturedDate = LocalDateTime.of(2024, 1, 1, 0, 0);
    PhotoMetadata meta = new PhotoMetadata(java.util.Optional.empty(), expectedCapturedDate, "test camara",
        "test manufaturer");
    File expectedFile = new File("test.jpg");
    String expectedTitle = "Test Photo";
    LocalDateTime expectedAddedDate = LocalDateTime.now();
    Photo photo = new Photo(expectedTitle, expectedAddedDate, meta, expectedFile);

    LocalDateTime actualCapturedDate = photo.capturedDate();
    assertEquals(expectedCapturedDate, actualCapturedDate);

    LocalDateTime actualAddedDate = photo.addedDate();
    assertEquals(expectedAddedDate, actualAddedDate);

    boolean isFavourite = photo.isFavourite();
    assertFalse(isFavourite);

    String actualTitle = photo.title();
    assertEquals(expectedTitle, actualTitle);

    Optional<? extends GPSCoordinates> actualPlace = photo.getPlace();
    assertEquals(Optional.empty(), actualPlace);
  }

  @Test
  void testCreatePhotoWithGPS() {
    LocalDateTime expectedCapturedDate = LocalDateTime.of(2024, 1, 1, 0, 0);
    GPSLocation expectedLocation = new GPSLocation(new double[] { -9.1930, 38.6959 }, "Lisbon");
    PhotoMetadata meta = new PhotoMetadata(java.util.Optional.of(expectedLocation), expectedCapturedDate, "test camara",
        "test manufaturer");
    File expectedFile = new File("test.jpg");
    String expectedTitle = "Test Photo";
    LocalDateTime expectedAddedDate = LocalDateTime.now();
    Photo photo = new Photo(expectedTitle, expectedAddedDate, meta, expectedFile);

    assertEquals(expectedCapturedDate, photo.capturedDate());
    assertEquals(expectedAddedDate, photo.addedDate());
    assertEquals(expectedTitle, photo.title());
    assertFalse(photo.isFavourite());
    Optional<? extends GPSCoordinates> place = photo.getPlace();
    assertTrue(place.isPresent());
    assertEquals(38.6959, place.get().latitude(), 0.000001);
    assertEquals(-9.1930, place.get().longitude(), 0.000001);
  }

  @Test
  void testToggleFavourite() {
    LocalDateTime expectedCapturedDate = LocalDateTime.of(2024, 1, 1, 0, 0);
    PhotoMetadata meta = new PhotoMetadata(java.util.Optional.empty(), expectedCapturedDate, "test camara",
        "test manufaturer");
    File expectedFile = new File("test.jpg");
    String expectedTitle = "Test Photo";
    LocalDateTime expectedAddedDate = LocalDateTime.now();
    Photo photo = new Photo(expectedTitle, expectedAddedDate, meta, expectedFile);

    photo.toggleFavourite();
    boolean isFavouriteAfterToggle1 = photo.isFavourite();
    photo.toggleFavourite();
    boolean isFavouriteAfterToggle2 = photo.isFavourite();

    // Assert
    assertTrue(isFavouriteAfterToggle1);
    assertFalse(isFavouriteAfterToggle2);
  }

  @Test
  void testSize() { // requires the use of a mock file class
    LocalDateTime expectedCapturedDate = LocalDateTime.of(2024, 1, 1, 0, 0);
    PhotoMetadata meta = new PhotoMetadata(java.util.Optional.empty(), expectedCapturedDate, "test camara",
        "test manufaturer");
    long expectedSize = 1024;
    MockFile expectedFile = new MockFile("test.jpg", expectedSize);
    String expectedTitle = "Test Photo";
    LocalDateTime expectedAddedDate = LocalDateTime.now();
    Photo photo = new Photo(expectedTitle, expectedAddedDate, meta, expectedFile);

    assertEquals(expectedSize, photo.size());
  }

  @Test
  void testNoMatches() {
    String regexp = "Exp.*";
    PhotoMetadata meta = new PhotoMetadata(java.util.Optional.empty(), LocalDateTime.now(), "", "");
    Photo photo = new Photo("Test Photo", LocalDateTime.now(), meta, new File("test.jpg"));

    boolean matches = photo.matches(regexp);
    assertFalse(matches);
  }

  @Test
  void testMatchesTitle() {
    LocalDateTime expectedCapturedDate = LocalDateTime.of(2024, 1, 1, 0, 0);
    PhotoMetadata meta = new PhotoMetadata(java.util.Optional.empty(), expectedCapturedDate, "test camara",
        "test manufaturer");
    long expectedSize = 1024;
    MockFile expectedFile = new MockFile("test.jpg", expectedSize);
    String expectedTitle = "Test Photo";
    LocalDateTime expectedAddedDate = LocalDateTime.now();
    Photo photo = new Photo(expectedTitle, expectedAddedDate, meta, expectedFile);

    assertEquals(expectedTitle, photo.title());
  }

  @Test
  void testMatchesFile() {
    LocalDateTime expectedCapturedDate = LocalDateTime.of(2024, 1, 1, 0, 0);
    PhotoMetadata meta = new PhotoMetadata(java.util.Optional.empty(), expectedCapturedDate, "test camara",
        "test manufaturer");
    long expectedSize = 1024;
    MockFile expectedFile = new MockFile("test.jpg", expectedSize);
    String expectedTitle = "Test Photo";
    LocalDateTime expectedAddedDate = LocalDateTime.now();
    Photo photo = new Photo(expectedTitle, expectedAddedDate, meta, expectedFile);

    assertEquals(expectedFile, photo.file());
  }

  @Test
  void testEquals() {
    File file1 = new File("test1.jpg");
    File file2 = new File("test2.jpg");
    File file3 = new File("test1.jpg");

    PhotoMetadata meta = new PhotoMetadata(Optional.empty(), LocalDateTime.of(2024, 1, 1, 0, 0), "cam", "man");
    LocalDateTime added = LocalDateTime.now();
    Photo photo1 = new Photo("Photo 1", added, meta, file1);
    Photo photo2 = new Photo("Photo 2", added, meta, file2);
    Photo photo3 = new Photo("Photo 3", added, meta, file3);

    assertTrue(photo1.equals(photo3));
    assertFalse(photo1.equals(photo2));
    assertTrue(photo1.equals(photo1));
    assertFalse(photo1.equals(null));
  }

  @Test
  void testHashCode() {
    File file1 = new File("test1.jpg");
    File file2 = new File("test2.jpg");

    PhotoMetadata meta = new PhotoMetadata(Optional.empty(), LocalDateTime.of(2024, 1, 1, 0, 0), "cam", "man");
    LocalDateTime added = LocalDateTime.now();
    Photo photo1a = new Photo("Photo 1a", added, meta, file1);
    Photo photo1b = new Photo("Photo 1b", added, meta, new File("test1.jpg"));
    Photo photo2 = new Photo("Photo 2", added, meta, file2);

    assertEquals(photo1a.hashCode(), photo1b.hashCode());
    assertFalse(photo1a.hashCode() == photo2.hashCode());
  }

}
