package com.example.VSAPIBot.DTO;


import com.example.VSAPIBot.Manager.Manager;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManagerDTO {
   private int id;
   private Long login;

   public static ManagerDTO from(Manager manager){
         return ManagerDTO.builder().id(manager.getId()).login(manager.getLogin()).build();
   }

   public static List<ManagerDTO> from(List<Manager> managers){
      return managers.stream().map(ManagerDTO::from).collect(Collectors.toList());
   }
}
