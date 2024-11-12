package com.ccsw.tutorial_game.game;

import com.ccsw.tutorial_game.game.model.Game;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @author ccsw
 *
 */
public interface GameRepository extends CrudRepository<Game, Long>, JpaSpecificationExecutor<Game> {

}
