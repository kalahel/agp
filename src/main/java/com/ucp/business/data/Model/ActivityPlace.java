package com.ucp.business.data.Model;

import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ActivityPlace extends TouristicSite{
    public ActivityPlace(String desc, Coordinates coord, double comfort){
        super(desc, coord, comfort);
    }
}
