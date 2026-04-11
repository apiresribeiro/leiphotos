package leiphotos.domain.albums;

import java.util.Collection;

public class AlbumsCatalog implements IAlbumsCatalog {

    private Collection<IAlbum> albums;

    public boolean createAlbum(String albumName){
        for (IAlbum album : this.albums){
            if (album.getName.equals(albumName)){
                return false; 
            }
        }
        /*IAlbum novoAlbum = new Album(albumName, lib)
        this.albums.add(novoAlbum)*/
        return true; 
    }

    public boolean deleteAlbum(String albumName){
        for (IAlbum album : this.albums){
            if (album.getName.equals(albumName)){
                this.albums.remove(album);
                return true
            }
        }
        return false;
    }

    public boolean containsAlbum(String albumName){
        for (IAlbum album : this.albums){
            if (album.getName.equals(albumName)){
                return true
            }
        }
        return false;
    }

    public boolean addPhotos(String albumName, Set<IPhoto> selectedPhotos){
        for (IAlbum album : this.albums){
            if (album.getName.equals(albumName)){
                albem.addPhotos(selectedPhotos);
                return true;
            }
        }
        return false;
    }

    public boolean removePhotos(String albumName, Set<IPhoto> selectedPhotos){
        for (IAlbum album : this.albums){
            if (album.getName.equals(albumName)){
                albem.removePhotos(selectedPhotos);
                return true;
            }
        }
        return false;
    }

    public List<IPhoto> getPhotos(String albumName){
        for (IAlbum album : this.albums){
            if (album.getName.equals(albumName)){
                return album.getPhotos();
            }
        }
    }

    public Set<String> getAlbumNames(){
        Set<String> nomesAlbuns = new Set<String>(); 
        for (IAlbum album : this.albums){
           nomesAlbuns.add(album.getName());
        }
    }

};
