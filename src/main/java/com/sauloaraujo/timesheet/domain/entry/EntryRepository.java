package com.sauloaraujo.timesheet.domain.entry;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Integer> {
	public List<Entry> findByDateBetween(Date start, Date end);
	public Entry findByProjectIdAndTaskIdAndDate(int projectId, int taskId, Date date);
}