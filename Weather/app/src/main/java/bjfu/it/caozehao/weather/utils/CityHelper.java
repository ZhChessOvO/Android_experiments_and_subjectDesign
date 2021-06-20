package bjfu.it.caozehao.weather.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class CityHelper {
    private static final String CITY_INFO_BEIJING = "101010100";
    private static CityHelper singleton;
    private HashMap<String, City> cityMap;

    private CityHelper() {
        initCityMap();
    }

    public static CityHelper getInstance() {
        if (singleton == null) {
            singleton = new CityHelper();
        }
        return singleton;
    }

    public static String getDefaultCityId() {
        return CITY_INFO_BEIJING;
    }

    public HashMap<String, City> getCityInfoMap() {
        return cityMap;
    }

    private void initCityMap() {
        HashMap map = new LinkedHashMap();
        map.put(CITY_INFO_BEIJING, new City("北京", "Beijing", "CITY_INFO_BEIJING"));
        map.put("101020100", new City("上海", "Shanghai", "101020100"));
        map.put("101030100", new City("天津", "Tianjin", "101030100"));
        map.put("101040100", new City("重庆", "Chongqing", "101040100"));
        map.put("101120101", new City("济南", "Jinan", "101120101"));
        map.put("101090101", new City("石家庄", "shijiazhuang", "101090101"));
        map.put("101060101", new City("长春", "Changchun", "101060101"));
        map.put("101050101", new City("哈尔滨", "Haerbin", "101050101"));
        map.put("101070101", new City("沈阳", "Shenyang", "101070101"));
        map.put("101080101", new City("呼和浩特", "Huhehaote", "101080101"));
        map.put("101130101", new City("乌鲁木齐", "Wulumuqi", "101130101"));
        map.put("101160101", new City("兰州", "Lanzhou", "101160101"));
        map.put("101170101", new City("银川", "Yinchuan", "101170101"));
        map.put("101100101", new City("太原", "Taiyuan", "101100101"));
        map.put("101110101", new City("西安", "Xian", "101110101"));
        map.put("101180101", new City("郑州", "Zhengzhou", "101180101"));
        map.put("101220101", new City("合肥", "Hefei", "101220101"));
        map.put("101190101", new City("南京", "Nanjing", "101190101"));
        map.put("101210101", new City("杭州", "Hangzhou", "101210101"));
        map.put("101230101", new City("福州", "Fuzhou", "101230101"));
        map.put("101280101", new City("广州", "Guangzhou", "101280101"));
        map.put("101240101", new City("南昌", "Nanchang", "101240101"));
        map.put("101310101", new City("海口", "Haikou", "101310101"));
        map.put("101300101", new City("南宁", "Nanning", "101300101"));
        map.put("101260101", new City("贵阳", "Guiyang", "101260101"));
        map.put("101250101", new City("长沙", "Changsha", "101250101"));
        map.put("101200101", new City("武汉", "Wuhan", "101200101"));
        map.put("101270101", new City("成都", "Chengdu", "101270101"));
        map.put("101290101", new City("昆明", "Kunming", "101290101"));
        map.put("101140101", new City("拉萨", "Lasa", "101140101"));
        map.put("101150101", new City("西宁", "Xining", "101150101"));
        map.put("101340101", new City("台北县", "Taibei", "101340101"));
        map.put("101320101", new City("香港", "Hongkong", "101320101"));
        map.put("101330101", new City("澳门", "Macao", "101330101"));
        cityMap = map;
    }
    // 获取城市中文名字列表, 展示使用
    public List<String> getCityNameList() {
        List<String> cityNameList = new ArrayList<>();
        for (Map.Entry<String, City> entry : cityMap.entrySet()) {
            cityNameList.add(entry.getValue().getName());
        }
        return cityNameList;
    }
    // 获取城市英文名字列表, 网络定位城市的时候使用
    public List<String> getCityNameEnList() {
        List<String> cityNameList = new ArrayList<>();
        for (Map.Entry<String, City> entry : cityMap.entrySet()) {
            cityNameList.add(entry.getValue().getNameEn());
        }
        return cityNameList;
    }
    // 获取城市id列表, 查询天气接口使用
    public List<String> getCityIdList() {
        List<String> cityIdList = new ArrayList<>();
        for (String id : cityMap.keySet()) {
            cityIdList.add(id);
        }
        return cityIdList;
    }

    public class City {
        public City(String name, String nameEn, String id) {
            this.name = name;
            this.nameEn = nameEn;
            this.id = id;
        }

        private String name;
        private String nameEn;
        private String id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNameEn() {
            return nameEn;
        }

        public void setNameEn(String nameEn) {
            this.nameEn = nameEn;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
