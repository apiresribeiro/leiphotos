package leiphotos.domain.core;

import java.util.ArrayList;
import java.util.Collection;
import java.time.Duration;
import java.time.LocalDateTime;
import leiphotos.domain.facade.IPhoto;

public class RecentlyDeletedLibrary extends ATrashLibrary {
    private Collection<IPhoto> photosApagadas; 
    private final int MAX_DATE = 30;
    private LocalDateTime dataAtual;
    private LocalDateTime dataCleaningTime;

    public RecentlyDeletedLibrary (){
        this.photosApagadas = new ArrayList<>();
        this.dataCleaningTime = LocalDateTime.now();
    }

    @Override
    public Collection<IPhoto> getPhotos() {
        return this.photosApagadas;
    }

    @Override
    public boolean deleteAll() {
        if (this.photosApagadas.isEmpty()){
            return false;
        }

        this.photosApagadas.clear();
        return true;
    }

    @Override
    public int getNumberOfPhotos() {
        return this.photosApagadas.size();
    }

     @Override
    public boolean addPhoto(IPhoto photo) {
        return this.photosApagadas.add(photo);
    }

    @Override
    public boolean deletePhoto(IPhoto photo) {
        return this.photosApagadas.remove(photo);
    }

    @Override
    public Collection<IPhoto> getMatches(String regexp) {
        Collection<IPhoto> matches = new ArrayList<>();
        for (IPhoto photoInLib : this.photosApagadas){
            if(photoInLib.matches(regexp))
                matches.add(photoInLib); 
        }
        return matches;        
    }

    @Override
    protected void clean() {
        if (cleaningTime()){
            for (IPhoto photo : this.photosApagadas){
                Duration duration = Duration.between(photo.addedDate(), dataAtual);
                if(duration.toDays() >= MAX_DATE){
                    this.deletePhoto(photo);
                }
            }
            this.dataCleaningTime = this.dataAtual;
        }
    }

    @Override
    protected boolean cleaningTime() {
        this.dataAtual = LocalDateTime.now();
        Duration duration = Duration.between(this.dataAtual, dataAtual);
        return duration.toDays() >= MAX_DATE;
    }
};
