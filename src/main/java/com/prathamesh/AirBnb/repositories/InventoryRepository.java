package com.prathamesh.AirBnb.repositories;

import com.prathamesh.AirBnb.entities.InventoryEntity;
import com.prathamesh.AirBnb.entities.RoomEntity;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {



    List<InventoryEntity> findByRoomOrderByDate(RoomEntity room);

    @Query("""
                SELECT i
                FROM InventoryEntity i
                WHERE i.room.id = :roomId
                  AND i.date BETWEEN :startDate and :endDate
            """)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<InventoryEntity> getInventoryAndLockBeforeUpdate(@Param("roomId") Long roomId,
                                                          @Param("startDate") LocalDate startDate,
                                                          @Param("endDate") LocalDate endDate);

    @Transactional
    @Modifying
    @Query("""
                UPDATE InventoryEntity i
                SET i.surgeFactor = :surgeFactor,
                    i.closed = :closed
                WHERE i.room.id = :roomId
                AND i.date BETWEEN :startDate AND :endDate
            """)
    void updateInventory(
            @Param("roomId") Long roomId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("closed") boolean closed,
            @Param("surgeFactor")BigDecimal surgeFactor
            );

    void deleteByRoom(RoomEntity room);
}
