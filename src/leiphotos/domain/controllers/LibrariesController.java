package leiphotos.domain.controllers;

import java.util.Optional;
import java.util.Set;

import leiphotos.domain.facade.ILibrariesController;
import leiphotos.domain.facade.IPhoto;

public class LibrariesController implements ILibrariesController {

    @Override
    public Optional<IPhoto> importPhoto(String title, String pathToPhotoFile) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'importPhoto'");
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
