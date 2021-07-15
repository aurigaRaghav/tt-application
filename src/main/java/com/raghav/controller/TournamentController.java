package com.raghav.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.raghav.constant.SiteConstant;
import com.raghav.dao.TournamentMatchTypesRepository;
import com.raghav.dao.TournamentRepository;
import com.raghav.entity.Tournament;
import com.raghav.entity.TournamentMatchTypes;
import com.raghav.request.TournamentRequest;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

	@Autowired
	TournamentRepository tourRepository;
	@Autowired
	TournamentMatchTypesRepository matchTypesRepo;
	private static String UPLOADED_FOLDER = "/home/auriga/Desktop/imageUpload/";

	@GetMapping("/all/{urlParameter}/{page}")
	public ModelAndView getTournamentList(@PathVariable("urlParameter") String urlParameter,
			@PathVariable("page") int page, Model model) {

		Pageable pageable = PageRequest.of(page, SiteConstant.PER_PAGE);

		Page<Tournament> allTournament = tourRepository.getTournamentsByStatus(urlParameter, pageable);
		model.addAttribute("tournaments", allTournament);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", allTournament.getTotalPages());
		model.addAttribute("prams", urlParameter);
		ModelAndView modelAndView = new ModelAndView("tournament/list-tournaments");
		return modelAndView;

	}

	@GetMapping("/detail/{id}")
	public ModelAndView tournamentDetail(@PathVariable("id") long id, Model model) {
		Tournament tournamentDetail = tourRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid tournament Id:" + id));
		System.out.println("Tournament Detail Obj : " + tournamentDetail);
		model.addAttribute("detail", tournamentDetail);
		model.addAttribute("matchTypes", tournamentDetail.getTournamentMatchTypes());
		ModelAndView modelAndView = new ModelAndView("tournament/detail");
		return modelAndView;

	}

	@GetMapping("/add")
	public ModelAndView createTournament(Model model) {
		// Tournament tournament = new Tournament();
		TournamentRequest tournament = new TournamentRequest();
		TournamentMatchTypes types = new TournamentMatchTypes();
		List<TournamentMatchTypes> matchTypes = matchTypesRepo.findAll();
		// System.out.println("match Type : " + matchTypes.toString());
		model.addAttribute("tournament", tournament);
		model.addAttribute("tournamentMatchTypes", matchTypes);
		model.addAttribute("types", types);
		ModelAndView modelAndView = new ModelAndView("tournament/add-tournament");
		return modelAndView;
	}

	@PostMapping("/save")
	public String saveTournament(TournamentRequest tournamentRequest, Model model,
			@RequestParam("image") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {

		System.out.println("request" + tournamentRequest);

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:/tournaments/add";
		}

		Date date = new Date();
		try {
			String filename = UPLOADED_FOLDER + file.getOriginalFilename();
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filename);
			Files.write(path, bytes);

			Tournament tournament = new Tournament();
			tournament.setStatus("upcoming");
			tournament.setName(tournamentRequest.getName());
			tournament.setStart_date(tournamentRequest.getStart_date());
			tournament.setRegistration_end_date(tournamentRequest.getRegistration_end_date());
			tournament.setSet_score(tournamentRequest.getSet_score());
			tournament.setImage(filename);

			// tournament.setCreated_on((java.sql.Date) date);
			tourRepository.save(tournament);

			// save match types

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/tournaments/all/upcoming/0";
	}

	@GetMapping("/create-draw")
	public ModelAndView getDrawScreen(@RequestParam("tournamentId") Long id,Model model) {
		Tournament tournamentDetail = tourRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid tournament Id:" + id));
		TournamentRequest tournament = new TournamentRequest();
		model.addAttribute("matchTypes", tournamentDetail.getTournamentMatchTypes());
		model.addAttribute("tournament", tournament);
		ModelAndView modelAndView = new ModelAndView("tournament/create-draw");
		return modelAndView;
	}

}
