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
	public List<Image> findAllImages(final String username) {
		final User user = userRepo.findUserByUsername(username);

		if (user.getRole().equalsIgnoreCase("admin")) {
			return imageRepo.findAll();
		}

		return null;
	}

	@Override
	public List<Image> findAllImagesByUser(final String username) {
		final User user = userRepo.findUserByUsername(username);

		if (user != null) {
			return imageRepo.findAllByUser(user);
		}

		return null;
	}

	@Override
	public Image findImagesByUsernameAndId(final String username, final int imageId) {
		final User user = userRepo.findUserByUsername(username);

		if (user != null) {
			final Optional<Image> imageOpt = imageRepo.findById(imageId);
			if (imageOpt.isPresent()) {
				return imageOpt.get();
			}
		}

		return null;
	}

	@Override
	public Image createImage(final Image image, final String username) {
		if (image == null) {
			return null;
		}

		final User user = userRepo.findUserByUsername(username);

		if (user != null) {
			image.setUser(user);
			return imageRepo.saveAndFlush(image);
		}

		return null;
	}

	@Override
	public Image updateImage(final Image image, final String username) {
		if (image == null || image.getUrlLink() == null) {
			return null;
		}

		final User user = userRepo.findUserByUsername(username);

		if (user != null) {
			final Optional<Image> imageOpt = imageRepo.findById(image.getId());
			if (imageOpt.isPresent()) {
				final Image managedImage = imageOpt.get();

				if (managedImage.getUser().equals(user)) {
					managedImage.setUrlLink(image.getUrlLink());
					return imageRepo.saveAndFlush(managedImage);
				}
			}
		}

		return null;
	}

	@Override
	public boolean destroyImage(final int imageId, final String username) {
		if (imageId <= 2) {
			return false;
		}

		final User user = userRepo.findUserByUsername(username);

		if (user != null) {
			final Optional<Image> imageOpt = imageRepo.findById(imageId);
			if (imageOpt.isPresent()) {
				final Image image = imageOpt.get();
				if (image.getUser().equals(user) || user.getRole().equalsIgnoreCase("admin")) {
					imageRepo.delete(image);
					return true;
				}
			}
		}

		return false;
	}

}
