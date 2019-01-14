package com.ucp.business.data;

import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ActivityPlace extends TouristicSite{
    public ActivityPlace(String desc, Coordinates coord){
        super(desc, coord);
    }
}
