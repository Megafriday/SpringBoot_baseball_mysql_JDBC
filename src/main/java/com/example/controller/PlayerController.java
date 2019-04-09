package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.entity.Player;
import com.example.service.PlayerService;

@Controller
public class PlayerController {

	@Autowired
	PlayerService playerService;


	@GetMapping("/players")
	public String getPlayers(Model model) {

		List<Player> players = playerService.findAll().orElse(null);
		model.addAttribute("players", players);

		return "index";
	}

	@GetMapping("/players/new")
	public String getPlayersNew(@ModelAttribute Player player) {
		return "new";
	}

	@PostMapping("/players")
	public String postPlayers(
			@ModelAttribute
			@Validated
			Player player,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) return "new";

		playerService.insert(player);

		return "redirect:/players";
	}

	@DeleteMapping("/players/{id}")
	public String deletePlayer( @PathVariable int id ) {

		playerService.delete(id);
		return "redirect:/players";

	}

	@GetMapping("/players/{id}/edit")
	public String getPlayersEdit(
			@PathVariable int id,
			Model model) {

		Player player = playerService.findOne(id).orElse(null);
		model.addAttribute("player", player);
		return "edit";
	}

	@PutMapping("/players/{id}")
	public String putPlayersEdit(
			@PathVariable int id,
			@ModelAttribute @Validated Player player,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) return "edit";

		player.setId(id);
		playerService.update(player);
		return "redirect:/players";
	}
}
