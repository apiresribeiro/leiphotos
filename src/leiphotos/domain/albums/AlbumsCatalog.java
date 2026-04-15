package leiphotos.domain.albums;

import leiphotos.domain.core.MainLibrary;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import leiphotos.domain.facade.IPhoto;

public class AlbumsCatalog implements IAlbumsCatalog {

    private HashMap<String, Album> nameToAlbum; 

    @Override
    public boolean createAlbum(String albumName){
        if (this.nameToAlbum.containsKey(albumName)){
            return false;
        }
        MainLibrary lib = new MainLibrary();
        Album newAlbum = new Album(albumName, lib);
        this.nameToAlbum.put(albumName, newAlbum);
        return true; 
    }

    @Override
    public boolean deleteAlbum(String albumName){
        if(this.nameToAlbum.containsKey(albumName)){
            Album albumToRemove = this.nameToAlbum.get(albumName); 
            this.nameToAlbum.remove(albumName, albumToRemove);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAlbum(String albumName){
       return this.nameToAlbum.containsKey(albumName);
    }

    @Override
    public boolean addPhotos(String albumName, Set<IPhoto> selectedPhotos){
        if (this.nameToAlbum.containsKey(albumName)){
            this.nameToAlbum.get(albumName).addPhotos(selectedPhotos);
            return true;
        }
        return false;
    }

    @Override
    public boolean removePhotos(String albumName, Set<IPhoto> selectedPhotos){
        if(this.nameToAlbum.containsKey(albumName)){
            this.nameToAlbum.get(albumName).removePhotos(selectedPhotos);
            return true;
        }
        return false;
    }

    @Override
    public List<IPhoto> getPhotos(String albumName){
        return this.nameToAlbum.get(albumName).getPhotos();
    }

    @Override
    public Set<String> getAlbumNames(){
        return this.nameToAlbum.keySet();
    }
};
