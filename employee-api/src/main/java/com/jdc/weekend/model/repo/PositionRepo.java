package com.jdc.weekend.model.repo;

import com.jdc.weekend.model.BaseRepository;
import com.jdc.weekend.model.entity.Position;

public interface PositionRepo extends BaseRepository<Position, String>{

	long countByCode(String value);

}
