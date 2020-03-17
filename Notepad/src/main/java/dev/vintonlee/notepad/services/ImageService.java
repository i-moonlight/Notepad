package dev.vintonlee.notepad.services;

import java.util.List;

import dev.vintonlee.notepad.entities.Image;

public interface ImageService {

	List<Image> findAllImages(String name);

	List<Image> findAllImagesByUser(String name);

	Image findImagesByUsernameAndId(String username, int imageId);

	Image createImage(Image image, String name);

	Image updateImage(Image image, String name);

	boolean destroyImage(int imageId, String name);
}
