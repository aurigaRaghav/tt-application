package com.raghav.controller;

import java.io.IOException;
import java.util.List;
import java.lang.String;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.raghav.dao.PlayerRepository;
import com.raghav.entity.Player;
import com.raghav.request.PlayerRequest;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//import com.raghav.dao.PlayerRepository;
//import com.raghav.entity.Player;

@Controller
@RequestMapping("/player")
public class PlayerController {

	@Autowired
	PlayerRepository playerRepo;

	private static String UPLOADED_FOLDER = "/home/auriga/Desktop/imageUpload/";

	@GetMapping("/")
	public String players(Model model) {
		List<Player> players = playerRepo.findAll();

		model.addAttribute("players", players);
		return "player/list-players";

	}

	@GetMapping("/add-player")
	public String addPlayer(Model model) {
		Player player = new Player();
		model.addAttribute("player", player);
		return "player/add-player";
	}

	@PostMapping("/save-player") //
	public String savePlayer(PlayerRequest playerRequest, @RequestParam("player_image") MultipartFile file,
			RedirectAttributes redirectAttributes) {

		// System.out.println("response of account opening::"+player.getPlayer_image());
		System.out.println("test" + playerRequest);
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:/player/add-player";
		}
//
		try {

			String filename = UPLOADED_FOLDER + file.getOriginalFilename();
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filename);
			Files.write(path, bytes);
			Player savePlayer = new Player();
			savePlayer.setName(playerRequest.getName());
			savePlayer.setEmail(playerRequest.getEmail());
			savePlayer.setAge(playerRequest.getAge());
			savePlayer.setGender(playerRequest.getGender());
			savePlayer.setImagePath(filename);
			Player savedPlayer = playerRepo.save(savePlayer);
			// Get the file and save it somewhere

//			redirectAttributes.addFlashAttribute("message",
//					"You successfully uploaded '" + file.getOriginalFilename() + "'");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/player/";
	}

	@GetMapping("/player-detail/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Player player = playerRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid player Id:" + id));

		model.addAttribute("player", player);
		return "player/player-detail";
	}

}
