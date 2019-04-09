package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.PlayerDao;
import com.example.entity.Player;

@Service
@Transactional
public class PlayerService {

	@Autowired
	PlayerDao playerDao;

	public Optional<List<Player>> findAll() {
		return playerDao.findAll();
	}

	public Optional<Player> findOne(int id) {
		return playerDao.findOne(id);
	}

	public int insert(Player player) {
		return playerDao.insert(player);
	}

	public int update(Player player) {
		return playerDao.update(player);
	}

	public int delete(int id) {
		return playerDao.delete(id);
	}
}
