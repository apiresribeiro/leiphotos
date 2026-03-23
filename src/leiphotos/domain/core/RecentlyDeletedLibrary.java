package leiphotos.domain.core;

import java.util.Collection;
import java.time.LocalDateTime;
import leiphotos.domain.facade.IPhoto;

public class RecentlyDeletedLibrary extends ATrashLibrary {
    private Collection<IPhoto> photosApagadas; 
    private Collection<IPhoto> matchesApagadas; 
    private final int MAX_DATE = 30;
    private LocalDateTime dataAtual = LocalDateTime.now();


    @Override
    public Collection<IPhoto> getPhotos() {
        return this.photosApagadas;
    }

    @Override
    public boolean deleteAll() {
        if (this.photosApagadas.isEmpty()){
            return false;
        }

        for (IPhoto photoInLib : photosApagadas){
            photosApagadas.remove(photoInLib);
        }
        return true;
    }

    @Override
    public int getNumberOfPhotos() {
        return this.photosApagadas.size();
    }

     @Override
    public boolean addPhoto(IPhoto photo) {
        for(IPhoto photoInLib : photosApagadas){
            if(photoInLib.equals(photo)){
                return false;
            }
        }
        photosApagadas.add(photo);
        return true; 
    }

    @Override
    public boolean deletePhoto(IPhoto photo) {
        for(IPhoto photoInLib : photosApagadas){
            if(photoInLib.equals(photo)){
                photosApagadas.remove(photo);
                return true;
            }
        }
        return false; 
    }

    @Override
    public Collection<IPhoto> getMatches(String regexp) {
        for (IPhoto photoInLib : photosApagadas){
            if(photoInLib.matches(regexp))
                matchesApagadas.add(photoInLib); 
        }
        return matchesApagadas;        
    }

    @Override
    protected void clean() {
        if (cleaningTime()){
            for (IPhoto photo : photosApagadas){
                if(photo.addedDate().compareTo(dataAtual) >= MAX_DATE){
                    this.deletePhoto(photo);
                }
            }
        }
    }

    @Override
    protected boolean cleaningTime() {
        for (IPhoto photo : photosApagadas){
            if(photo.addedDate().compareTo(dataAtual) >= MAX_DATE){
                return true;
            }
        }
        return false;
    }
};
