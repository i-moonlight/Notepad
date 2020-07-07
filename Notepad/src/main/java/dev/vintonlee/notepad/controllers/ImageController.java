package dev.vintonlee.notepad.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.vintonlee.notepad.entities.Image;
import dev.vintonlee.notepad.services.ImageService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4209" })
public class ImageController {

	@Autowired
	private ImageService imageSvc;

	@GetMapping("images")
	public List<Image> findAllImagesByUser(final HttpServletRequest req, final HttpServletResponse resp,
			final Principal principal) {

		final List<Image> images = imageSvc.findAllImagesByUser(principal.getName());

		if (images == null) {
			resp.setStatus(404);
		}
		if (images != null && images.isEmpty()) {
			resp.setStatus(204);
		}

		return images;
	}

	@GetMapping("images/{imageId}")
	public Image findAllImagesByUsernameandImageId(@PathVariable("imageId") final Integer imageId,
			final HttpServletRequest req, final HttpServletResponse resp, final Principal principal) {

		final Image image = imageSvc.findImagesByUsernameAndId(principal.getName(), imageId);

		if (image == null) {
			resp.setStatus(404);
		}

		return image;
	}

	@GetMapping("images/admin")
	public List<Image> findAll(final HttpServletRequest req, final HttpServletResponse resp,
			final Principal principal) {

		final List<Image> images = imageSvc.findAllImages(principal.getName());

		if (images == null) {
			resp.setStatus(401);
		}
		if (images != null && images.isEmpty()) {
			resp.setStatus(204);
		}

		return images;
	}

	@PostMapping("images")
	public Image createImage(@RequestBody Image image, final HttpServletRequest req, final Principal principal,
			final HttpServletResponse resp) {

		try {
			image = imageSvc.createImage(image, principal.getName());
			if (image == null) {
				resp.setStatus(404);
				return null;
			}
			resp.setStatus(202);
			final StringBuffer url = req.getRequestURL();
			url.append("/").append(image.getId());
			resp.addHeader("Location", url.toString());
		} catch (final Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			return null;
		}

		return image;
	}

	@PutMapping("images")
	public Image updateImage(@RequestBody Image image, final HttpServletRequest req, final Principal principal,
			final HttpServletResponse resp) {

		try {
			image = imageSvc.updateImage(image, principal.getName());
			if (image == null) {
				resp.setStatus(404);
				return null;
			}
			resp.setStatus(202);
			final StringBuffer url = req.getRequestURL();
			url.append("/").append(image.getId());
			resp.addHeader("Location", url.toString());
		} catch (final Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			return null;
		}

		return image;
	}

	@DeleteMapping("images/{imageId}")
	public Image destroyImage(@PathVariable("imageId") final int imageId, final HttpServletRequest req,
			final Principal principal, final HttpServletResponse resp) {
		boolean image = false;

		try {
			image = imageSvc.destroyImage(imageId, principal.getName());
			if (!image) {
				resp.setStatus(404);
				return null;
			}
			resp.setStatus(202);

		} catch (final Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			return null;
		}

		return null;
	}

}
