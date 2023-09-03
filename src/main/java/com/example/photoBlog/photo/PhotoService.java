package com.example.photoBlog.photo;


// import java.util.Arrays;
import java.util.List;
// import com.example.photoBlog.photo.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PhotoService {

	private final PhotoRepository photoRepository;
	
	@Autowired
    public PhotoService(PhotoRepository photoRepository) {
		this.photoRepository = photoRepository;
	}


	public List<Photo> getPhotos() {	
		return photoRepository.findAll();	
	}


	public void addNewPhoto(Photo photo) {
		// System.out.println(photo);
		photoRepository.save(photo);
	} 
}
