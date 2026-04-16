package leiphotos.domain.controllers;

import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Set;

import leiphotos.domain.facade.ILibrariesController;
import leiphotos.domain.facade.IPhoto;
import leiphotos.domain.core.PhotoFactory;

public class LibrariesController implements ILibrariesController {

    @Override
    public Optional<IPhoto> importPhoto(String title, String pathToPhotoFile) {
        try {
            IPhoto newPhoto = PhotoFactory.INSTANCE.createPhoto(title, pathToPhotoFile);
            return Optional.of(newPhoto);
        } catch (FileNotFoundException e) {
            return Optional.empty();
        }
    }

    @Override
    public void deletePhotos(Set<IPhoto> selectedPhotos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePhotos'");
    }

    @Override
    public void emptyTrash() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'emptyTrash'");
    }

    @Override
    public void toggleFavourite(Set<IPhoto> selectedPhotos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toggleFavourite'");
    }

    @Override
    public Iterable<IPhoto> getMatches(String regExp) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMatches'");
    }

};
