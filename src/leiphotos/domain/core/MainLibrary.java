package leiphotos.domain.core;

import java.util.ArrayList;
import java.util.Collection;

import leiphotos.domain.core.events.PhotoLibraryEvent;
import leiphotos.domain.facade.IPhoto;
import leiphotos.utils.AbsSubject;

public class MainLibrary extends AbsSubject<PhotoLibraryEvent> implements Library {
    private Collection<IPhoto> lib; 
    private Collection<IPhoto> matches;

    public MainLibrary(){
        this.lib = new ArrayList<>();
        this.matches = new ArrayList<>();
            
    }

    @Override
    public int getNumberOfPhotos() {
        return lib.size();
    }

    @Override
    public boolean addPhoto(IPhoto photo) {
        return lib.add(photo);
    }

    @Override
    public boolean deletePhoto(IPhoto photo) {
        return this.lib.remove(photo);
    }

    @Override
    public Collection<IPhoto> getPhotos() {
        return this.lib;
    }

    @Override
    public Collection<IPhoto> getMatches(String regexp) {
        for (IPhoto photoInLib : lib){
            if(photoInLib.matches(regexp))
                matches.add(photoInLib); 
        }
        return matches;        
    }

};
