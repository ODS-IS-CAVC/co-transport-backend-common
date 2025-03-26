package com.next.logistic.data.dto.request;

import java.util.List;
import java.util.Map;

/**
 * <PRE>
 * コントローラークラス。<BR> リクエストDTOを定義する。
 * </PRE>
 *
 * @author Next Logistics
 */
public class ConstantRequestDTO {

    public static final String DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
    public static final String TARGET_TIME = "targetTime";
    public static final String REQUEST_INFO = "requestInfo";

    // distance 1-1
    public static final Map<String, Object> REQUEST_1 = Map.of(
        "requestInfo", List.of(
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29019/12962")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29018/12962")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29017/12961")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29017/12962")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29016/12962")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29016/12961")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29015/12961")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29013/12961")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29014/12961")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29014/12960")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29013/12960")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29012/12960")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29019/12962")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29018/12962")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29017/12961")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29017/12962")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29016/12962")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29016/12961")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29015/12961")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29013/12961")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29014/12961")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29014/12960")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29013/12960")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29012/12960")
            )
        )
    );
    // distance 1-2
    public static final Map<String, Object> REQUEST_2 = Map.of(
        "requestInfo", List.of(
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29012/12959")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29011/12959")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29011/12958")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29010/12957")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29010/12958")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29009/12958")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29009/12957")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29008/12957")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29007/12957")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29007/12956")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29006/12956")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29012/12959")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29011/12959")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29011/12958")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29010/12957")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29010/12958")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29009/12958")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29009/12957")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29008/12957")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29007/12957")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29007/12956")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29006/12956")
            )
        )
    );
    // distance 2-1
    public static final Map<String, Object> REQUEST_3 = Map.of(
        "requestInfo", List.of(
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29005/12956")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29004/12956")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29004/12957")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29003/12957")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29002/12958")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29002/12957")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29001/12958")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29000/12958")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29000/12959")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29000/12960")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28999/12960")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28998/12960")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28998/12961")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28997/12961")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28996/12961")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28995/12961")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29005/12956")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29004/12956")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29004/12957")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29003/12957")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29002/12958")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29002/12957")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29001/12958")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29000/12958")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29000/12959")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/29000/12960")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28999/12960")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28998/12960")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28998/12961")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28997/12961")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28996/12961")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28995/12961")
            )
        )
    );
    // distance 2-2
    public static final Map<String, Object> REQUEST_4 = Map.of(
        "requestInfo", List.of(
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28994/12961")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28994/12962")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28993/12962")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28992/12962")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28992/12963")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28991/12963")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28991/12964")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28991/12965")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28990/12965")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28989/12965")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28989/12966")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28989/12967")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28988/12967")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28988/12968")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28988/12969")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28987/12969")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28994/12961")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28994/12962")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28993/12962")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28992/12962")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28992/12963")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28991/12963")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28991/12964")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28991/12965")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28990/12965")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28989/12965")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28989/12966")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28989/12967")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28988/12967")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28988/12968")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28988/12969")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28987/12969")
            )
        )
    );
    // distance 3-1
    public static final Map<String, Object> REQUEST_5 = Map.of(
        "requestInfo", List.of(
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28987/12970")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28986/12970")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28985/12971")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28986/12971")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28986/12972")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28984/12973")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28985/12972")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28984/12972")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28983/12972")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28983/12973")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28982/12973")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28982/12974")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28981/12974")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28980/12974")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28979/12974")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28978/12974")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28987/12970")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28986/12970")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28985/12971")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28986/12971")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28986/12972")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28984/12973")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28985/12972")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28984/12972")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28983/12972")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28983/12973")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28982/12973")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28982/12974")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28981/12974")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28980/12974")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28979/12974")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28978/12974")
            )
        )
    );
    // distance 3-2
    public static final Map<String, Object> REQUEST_6 = Map.of(
        "requestInfo", List.of(
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28978/12975")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28977/12975")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28977/12976")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28976/12976")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28976/12977")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28975/12977")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28974/12977")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28974/12978")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28973/12978")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28973/12979")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28972/12979")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28972/12981")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28972/12980")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28971/12980")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28971/12981")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28978/12975")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28977/12975")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28977/12976")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28976/12976")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28976/12977")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28975/12977")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28974/12977")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28974/12978")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28973/12978")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28973/12979")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28972/12979")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28972/12981")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28972/12980")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28971/12980")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28971/12981")
            )
        )
    );
    // distance 4-1
    public static final Map<String, Object> REQUEST_7 = Map.of(
        "requestInfo", List.of(
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28971/12982")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28971/12983")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28971/12984")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28970/12984")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28970/12985")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28970/12986")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28970/12987")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28969/12987")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28969/12988")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28968/12988")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28968/12989")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28967/12989")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28967/12990")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28966/12990")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28966/12991")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28965/12991")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28971/12982")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28971/12983")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28971/12984")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28970/12984")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28970/12985")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28970/12986")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28970/12987")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28969/12987")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28969/12988")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28968/12988")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28968/12989")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28967/12989")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28967/12990")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28966/12990")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28966/12991")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28965/12991")
            )
        )
    );
    // distance 4-2
    public static final Map<String, Object> REQUEST_8 = Map.of(
        "requestInfo", List.of(
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28964/12991")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28964/12992")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28963/12992")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28962/12992")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28961/12992")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28961/12993")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28960/12993")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28959/12993")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28959/12994")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28958/12994")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28958/12995")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28957/12995")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28956/12995")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28956/12996")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28955/12996")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28964/12991")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28964/12992")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28963/12992")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28962/12992")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28961/12992")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28961/12993")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28960/12993")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28959/12993")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28959/12994")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28958/12994")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28958/12995")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28957/12995")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28956/12995")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28956/12996")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28955/12996")
            )
        )
    );
    // distance 5-1
    public static final Map<String, Object> REQUEST_9 = Map.of(
        "requestInfo", List.of(
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28954/12996")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28953/12996")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28952/12996")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28951/12996")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28950/12996")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28949/12997")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28949/12996")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28948/12996")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28948/12997")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28947/12997")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28947/12996")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28946/12996")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28945/12996")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28944/12996")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28954/12996")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28953/12996")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28952/12996")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28951/12996")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28950/12996")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28949/12997")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28949/12996")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28948/12996")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28948/12997")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28947/12997")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28947/12996")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28946/12996")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28945/12996")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28944/12996")
            )
        )
    );
    // distance 5-2
    public static final Map<String, Object> REQUEST_10 = Map.of(
        "requestInfo", List.of(
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28944/12997")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28943/12997")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28942/12997")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28941/12997")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28941/12998")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28940/12998")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28939/12999")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28939/12998")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28938/12998")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28938/12999")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28937/12999")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28937/12998")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28936/12998")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28944/12997")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28943/12997")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28942/12997")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28941/12997")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28941/12998")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28940/12998")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28939/12999")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28939/12998")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28938/12998")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28938/12999")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28937/12999")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28937/12998")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28936/12998")
            )
        )
    );
    // distance 6-1
    public static final Map<String, Object> REQUEST_11 = Map.of(
        "requestInfo", List.of(
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28935/12998")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28934/12998")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28933/12998")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28932/12998")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28931/12998")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28930/12998")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28930/12997")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28929/12998")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28929/12997")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28928/12997")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28935/12998")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28934/12998")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28933/12998")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28932/12998")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28931/12998")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28930/12998")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28930/12997")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28929/12998")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28929/12997")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28928/12997")
            )
        )
    );
    // distance 6-2
    public static final Map<String, Object> REQUEST_12 = Map.of(
        "requestInfo", List.of(
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28927/12997")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28926/12997")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28925/12997")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28924/12997")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28923/12996")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28923/12997")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28922/12997")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28922/12996")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28921/12997")
            ),
            Map.of(
                "targetData", 3,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28921/12996")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28927/12997")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28926/12997")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28925/12997")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28924/12997")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28923/12996")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28923/12997")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28922/12997")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28922/12996")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28921/12997")
            ),
            Map.of(
                "targetData", 2,
                "requestFormat", 1,
                "requestArea", Map.of("spatialID", "15/0/28921/12996")
            )
        )
    );
}
