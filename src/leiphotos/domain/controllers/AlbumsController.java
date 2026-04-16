package leiphotos.domain.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import leiphotos.domain.albums.IAlbumsCatalog;
import leiphotos.domain.facade.IAlbumsController;
import leiphotos.domain.facade.IPhoto;

public class AlbumsController implements IAlbumsController {

    IAlbumsCatalog catalogAlbums;
    String nomeAlbumSelecionado;

    public AlbumsController(IAlbumsCatalog catalog){
        this.catalogAlbums = catalog;
        this.nomeAlbumSelecionado = null;
    }

    @Override
    public boolean createAlbum(String name) {
        return catalogAlbums.createAlbum(name);
    }

    @Override
    public void removeAlbum() {
        if(this.nomeAlbumSelecionado!=null){
            this.catalogAlbums.removePhotos(nomeAlbumSelecionado, new HashSet<>(this.catalogAlbums.getPhotos(nomeAlbumSelecionado)));
        }
    }

    @Override
    public void selectAlbum(String name) {
        this.nomeAlbumSelecionado = name;
    }

    @Override
    public void addPhotos(Set<IPhoto> selectedPhotos) {
        if(this.nomeAlbumSelecionado!=null){
            this.catalogAlbums.addPhotos(this.nomeAlbumSelecionado, selectedPhotos);
        }
    }

    @Override
    public void removePhotos(Set<IPhoto> selectedPhotos) {
        if(this.nomeAlbumSelecionado!=null){
            this.catalogAlbums.removePhotos(this.nomeAlbumSelecionado, selectedPhotos);
        }
    }

    @Override
    public List<IPhoto> getPhotos() {
        if(this.nomeAlbumSelecionado!=null){
            return this.catalogAlbums.getPhotos(nomeAlbumSelecionado);
        }
        return null;
    }

    @Override
    public Optional<String> getSelectedAlbum() {
        if (this.nomeAlbumSelecionado != null){
            return Optional.of(this.nomeAlbumSelecionado);
        }
        return Optional.empty();
    }

    @Override
    public boolean createSmartAlbum(String name, Predicate<IPhoto> criteria) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createSmartAlbum'");
    }

    @Override
    public Set<String> getAlbumNames() {
        return this.catalogAlbums.getAlbumNames();
    }

};
