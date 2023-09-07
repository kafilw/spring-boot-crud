package com.example.photoBlog.photo;


// import java.util.Arrays;
import java.util.List;
// import com.example.photoBlog.photo.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


	public ResponseEntity<Photo> addNewPhoto(Photo photo) {
		// System.out.println(photo);
		Photo savedPhoto = photoRepository.save(photo);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedPhoto);

	} 
}
