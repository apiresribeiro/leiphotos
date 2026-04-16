package leiphotos.domain.controllers;

import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Set;

import leiphotos.domain.facade.ILibrariesController;
import leiphotos.domain.facade.IPhoto;
import leiphotos.domain.facade.ViewsType;
import leiphotos.domain.views.ILibraryView;
import leiphotos.domain.views.IViewsCatalog;
import leiphotos.domain.core.MainLibrary;
import leiphotos.domain.core.PhotoFactory;
import leiphotos.domain.core.TrashLibrary;

public class LibrariesController implements ILibrariesController {

    MainLibrary mainLibrary;
    TrashLibrary trashLibrary;
    IViewsCatalog viewsCatalog;

    public LibrariesController (MainLibrary main, TrashLibrary trash, IViewsCatalog views){
        this.mainLibrary = main; 
        this.trashLibrary = trash;
        this.viewsCatalog = views; 
    }

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
        for(IPhoto photo : selectedPhotos){
            if(this.mainLibrary.containsPhoto(photo)){
                this.mainLibrary.deletePhoto(photo);
            }
        }
    }

    @Override
    public void emptyTrash() {
        this.trashLibrary.deleteAll();
    }

    @Override
    public void toggleFavourite(Set<IPhoto> selectedPhotos) {
        for (IPhoto photo : selectedPhotos){
            photo.toggleFavourite();
        }
    }

    @Override
    public Iterable<IPhoto> getMatches(String regExp) {
        ILibraryView vista = this.viewsCatalog.getView(ViewsType.ALL_MAIN);
        return vista.getMatches(regExp);
    }

};
