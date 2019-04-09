package com.example.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.entity.Player;

@Repository
public class PlayerDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Optional<List<Player>> findAll() {

		String sql = "select * from player";

		BeanPropertyRowMapper<Player> rowMapper
		= new BeanPropertyRowMapper<Player>(Player.class);

		List<Player> players = jdbcTemplate.query(sql, rowMapper);

		return Optional.ofNullable(players);
	}

	public Optional<Player> findOne(int id) {

		String sql = "select * from player where id = ?";

		BeanPropertyRowMapper<Player> rowMapper
		= new BeanPropertyRowMapper<Player>(Player.class);

		Player player = jdbcTemplate.queryForObject(sql,rowMapper,id);

		return Optional.ofNullable(player);
	}

	public int update(Player player) {
		String sql = "update player set" +
				" name=?, age=?, team=?, position=? where id=?";

		return jdbcTemplate.update(sql,
				player.getName(),
				player.getAge(),
				player.getTeam(),
				player.getPosition(),
				player.getId() );
	}

	public int insert(Player player) {

		String sql = "insert into player(name, age, team, position)" +
				" values( ?, ?, ?, ? )";

		return jdbcTemplate.update(sql,
				player.getName(),
				player.getAge(),
				player.getTeam(),
				player.getPosition() );
	}

	public int delete(int id) {

		String sql = "delete from player where id = ?";

		return jdbcTemplate.update(sql, id);

	}

}
