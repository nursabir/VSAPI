package com.example.VSAPIBot.DTO;

import com.example.VSAPIBot.Moderator.Moderator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.Banner;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModeratorDTO {
    private Long login;

    public static ModeratorDTO from(Moderator moderator){
         return ModeratorDTO.builder().login(moderator.getLogin()).build();
    }

    public static List<ModeratorDTO> from(List<Moderator> moderators){
        return moderators.stream().map(ModeratorDTO::from).collect(Collectors.toList());
    }
}
