package com.example.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Player;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PlayerDaoTest {

	@Autowired
	PlayerDao playerDao;

	@Test
	@Sql("/insertPlayer.sql")
	public void testFindAll() {

		Optional<List<Player>> optional = playerDao.findAll();
		List<Player> players = optional.orElse(null);
		Player player = players.get(0);
		System.out.println(player);

		assertEquals(player.getName(), "星飛雄馬");

	}

	@Test
	@Sql("/insertPlayer.sql")
	public void testFindOne() {

		Player player = playerDao.findOne(1).orElse(null);
		System.out.println(player);

		assertEquals(player.getName(), "星飛雄馬");
	}

	@Test
	public void testInsert() {

		Player player = new Player();
		player.setId(2);
		player.setName("花形満");
		player.setAge(22);
		player.setTeam("阪神タイガース");
		player.setPosition("ファースト");

		int result = playerDao.insert(player);
		List<Player> players = playerDao.findAll().orElse(null);
		System.out.println(players);
		assertEquals(1, result);
	}


	@Test
	@Sql("/insertPlayer.sql")
	public void testUpdate() {

		Player player = new Player();
		player.setId(1);
		player.setName("花形満");
		player.setAge(22);
		player.setTeam("阪神タイガース");
		player.setPosition("ファースト");

		int result = playerDao.update(player);
		List<Player> players = playerDao.findAll().orElse(null);
		System.out.println(players);
		assertEquals(1, result);
	}

	@Test
	@Sql("/insertPlayer.sql")
	public void testDelete() {

		int result = playerDao.delete(1);
		List<Player> players = playerDao.findAll().orElse(null);
		System.out.println(players);
		assertEquals(1, result);
	}

}
