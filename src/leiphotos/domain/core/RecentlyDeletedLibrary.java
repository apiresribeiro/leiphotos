package leiphotos.domain.core;

import java.util.Collection;

import leiphotos.domain.facade.IPhoto;

public class RecentlyDeletedLibrary extends ATrashLibrary {

    @Override
    public Collection<IPhoto> getPhotos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPhotos'");
    }

    @Override
    public boolean deleteAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

    @Override
    public int getNumberOfPhotos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNumberOfPhotos'");
    }

    @Override
    public boolean addPhoto(IPhoto photo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addPhoto'");
    }

    @Override
    public boolean deletePhoto(IPhoto photo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePhoto'");
    }

    @Override
    public Collection<IPhoto> getMatches(String regexp) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMatches'");
    }

    @Override
    protected void clean() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clean'");
    }

    @Override
    protected boolean cleaningTime() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cleaningTime'");
    }

};
