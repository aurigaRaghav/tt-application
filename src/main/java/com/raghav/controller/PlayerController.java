package com.raghav.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.raghav.dao.PlayerRepository;
import com.raghav.entity.Player;
import com.raghav.request.PlayerRequest;
import com.raghav.response.Response;


//import com.raghav.dao.PlayerRepository;
//import com.raghav.entity.Player;

@RestController
@RequestMapping("/player")
public class PlayerController {

	@Autowired
	PlayerRepository playerRepo;

	private static String UPLOADED_FOLDER = "/home/auriga/Desktop/imageUpload/";

	@GetMapping("/")
	public ModelAndView players(Model model) {
		List<Player> players = playerRepo.findAll();

		model.addAttribute("players", players);
		ModelAndView modelAndView = new ModelAndView("player/list-players");
		return modelAndView;

	}

	@GetMapping("/add-player")
	public ModelAndView addPlayer(Model model) {
		Player player = new Player();
		model.addAttribute("player", player);
		ModelAndView modelAndView = new ModelAndView("player/add-player");
		return modelAndView;
	}

	@PostMapping("/save-player") //
	public ModelAndView savePlayer(PlayerRequest playerRequest, @RequestParam("player_image") MultipartFile file,
			RedirectAttributes redirectAttributes) {

		// System.out.println("response of account opening::"+player.getPlayer_image());
		System.out.println("test" + playerRequest);
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			ModelAndView modelAndView = new ModelAndView("redirect:/player/add-player");
			return modelAndView;
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
		ModelAndView modelAndView = new ModelAndView("redirect:/player/");
		return modelAndView;
	}

	@GetMapping("/player-detail/{id}")
	public ModelAndView showUpdateForm(@PathVariable("id") long id, Model model) {
		Player player = playerRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid player Id:" + id));

		model.addAttribute("player", player);
		ModelAndView modelAndView = new ModelAndView("player/player-detail");
		return modelAndView;
	}
	
	@PostMapping("/get-filter-players")
	public Response filterPlayer(@RequestParam("search") String searchStr,Model model) {
		System.out.println(searchStr);
		List<Player> filterPlayer = playerRepo.filterPlayers(searchStr);
		System.out.println("filterPlayers : " + filterPlayer);
		model.addAttribute("players", filterPlayer);
		//ModelAndView modelAndView = new ModelAndView("player/partials/_filterPlayers");
		Response response = new Response("success", filterPlayer);
		return response;
	}

}
