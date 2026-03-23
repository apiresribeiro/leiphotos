package leiphotos.domain.core;

import java.util.Collection;

import leiphotos.domain.core.events.PhotoLibraryEvent;
import leiphotos.domain.facade.IPhoto;
import leiphotos.utils.AbsSubject;

public class MainLibrary extends AbsSubject<PhotoLibraryEvent> implements Library {
    private Collection<IPhoto> lib; 
    private Collection<IPhoto> matches;

    @Override
    public int getNumberOfPhotos() {
        return lib.size();
    }

    @Override
    public boolean addPhoto(IPhoto photo) {
        for(IPhoto photoInLib : lib){
            if(photoInLib.equals(photo))
                return false;
        }
        lib.add(photo);
        return true; 
    }

    @Override
    public boolean deletePhoto(IPhoto photo) {
        for(IPhoto photoInLib : lib){
            if(photoInLib.equals(photo))
                lib.remove(photo);
                return true; 
        }
        return false; 
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
