package dev.vintonlee.notepad.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.vintonlee.notepad.entities.Image;
import dev.vintonlee.notepad.entities.User;
import dev.vintonlee.notepad.repositories.ImageRepository;
import dev.vintonlee.notepad.repositories.UserRepository;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageRepository imageRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Image> findAllImages(String username) {
		User user = userRepo.findUserByUsername(username);

		if (user.getRole().equalsIgnoreCase("admin")) {
			return imageRepo.findAll();
		}

		return null;
	}

	@Override
	public List<Image> findAllImagesByUser(String username) {
		User user = userRepo.findUserByUsername(username);

		if (user != null) {
			return imageRepo.findAllByUser(user);
		}

		return null;
	}

	@Override
	public Image findImagesByUsernameAndId(String username, int imageId) {
		User user = userRepo.findUserByUsername(username);

		if (user != null) {
			Optional<Image> imageOpt = imageRepo.findById(imageId);
			if (imageOpt.isPresent()) {
				Image image = imageOpt.get();
				return image;
			}
		}

		return null;
	}

	@Override
	public Image createImage(Image image, String username) {
		if (image == null) {
			return null;
		}

		User user = userRepo.findUserByUsername(username);

		if (user != null) {
			image.setUser(user);
			return imageRepo.saveAndFlush(image);
		}

		return null;
	}

	@Override
	public Image updateImage(Image image, String username) {
		if (image == null || image.getUrlLink() == null) {
			return null;
		}

		User user = userRepo.findUserByUsername(username);

		if (user != null) {
			Optional<Image> imageOpt = imageRepo.findById(image.getId());
			if (imageOpt.isPresent()) {
				Image managedImage = imageOpt.get();

				if (managedImage.getUser().equals(user)) {
					managedImage.setUrlLink(image.getUrlLink());
					return imageRepo.saveAndFlush(managedImage);
				}
			}
		}

		return null;
	}

	@Override
	public boolean destroyImage(int imageId, String username) {
		if (imageId <= 2) {
			return false;
		}

		User user = userRepo.findUserByUsername(username);

		if (user != null) {
			Optional<Image> imageOpt = imageRepo.findById(imageId);
			if (imageOpt.isPresent()) {
				Image image = imageOpt.get();
				if (image.getUser().equals(user) || user.getRole().equalsIgnoreCase("admin")) {
					imageRepo.delete(image);
					return true;
				}
			}
		}

		return false;
	}

}
