package com.example.tyudy.ticket2rideclient.common.cities;

import android.graphics.PointF;
import android.util.Pair;

import com.example.tyudy.ticket2rideclient.model.ClientModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.example.tyudy.ticket2rideclient.common.Color.BLACK;
import static com.example.tyudy.ticket2rideclient.common.Color.BLUE;
import static com.example.tyudy.ticket2rideclient.common.Color.COLORLESS;
import static com.example.tyudy.ticket2rideclient.common.Color.GREEN;
import static com.example.tyudy.ticket2rideclient.common.Color.ORANGE;
import static com.example.tyudy.ticket2rideclient.common.Color.PURPLE;
import static com.example.tyudy.ticket2rideclient.common.Color.RED;
import static com.example.tyudy.ticket2rideclient.common.Color.WHITE;
import static com.example.tyudy.ticket2rideclient.common.Color.YELLOW;

/**
 * Created by Trevor on 3/8/2017.
 */

public class City {
    /**
     * A map of the cities this city is connected to, with the
     * values being the class Path
     */
    private String mCityName;
    private ArrayList<Path> mPaths;
    private PointF mCoordinates;

    public City(String cityName) {
        mCityName = cityName;

    }

    public void setCoordinate(PointF coordinate) { mCoordinates = coordinate; }
    public PointF getCoordinates() { return mCoordinates; }
    public String getCityName() { return mCityName; }

    public void setPaths(ArrayList<Path> paths) { this.mPaths = paths; }
    public ArrayList<Path> getPaths() { return mPaths; }

    /**
     * A function to findif a city is connected to another
     * @param city The city the question is about
     * @return True if the two are connected, false otherwise
     */
    public boolean isConnectedTo(City city) {
        if (city.equals(this))
            return true;

        for (Path path : mPaths)
        {
            if (path.containsCity(city))
                return true;
        }

        return false;
    }

    /**
     * Getter function to return the path between the two cities
     * @param city The connected city to get the path to
     * @return Path to the given city, if connected. Null if no connection exists,
     *  or if the given city is the current city
     */
    public Path getPathTo(City city) {
        if (city.equals(this))
            return null;

        if (isConnectedTo(city))
        {
            for (Path path : mPaths)
            {
                if (path.containsCity(city))
                    return path;
            }
        }

        return null;
    }

    /**
     * Needs to be called during game set-up
     */
    public static void initAllCities() {
        TreeMap<String, City> cities = new TreeMap<>();
        ArrayList<Path> cityPaths = new ArrayList<Path>();

        City Atlanta = new City("Atlanta");
        City Boston = new City("Boston");
        City Calgary = new City("Calgary");
        City Charleston = new City("Charleston");
        City Chicago = new City("Chicago");
        City Dallas = new City("Dallas");
        City Denver = new City("Denver");
        City Duluth = new City("Duluth");
        City El_Paso = new City("El Paso");
        City Helena = new City("Helena");
        City Houston = new City("Houston");
        City Kansas_City = new City("Kansas City");
        City Las_Vegas = new City("Las Vegas");
        City Little_Rock = new City("Little Rock");
        City Los_Angeles = new City("Los Angeles");
        City Miami = new City("Miami");
        City Montreal = new City("Montreal");
        City Nashville = new City("Nashville");
        City New_Orleans = new City("New Orleans");
        City New_York = new City("New York");
        City Oklahoma_City = new City("Oklahoma City");
        City Omaha = new City("Omaha");
        City Phoenix = new City("Phoenix");
        City Pittsburgh = new City("Pittsburgh");
        City Portland = new City("Portland");
        City Raleigh = new City("Raleigh");
        City St_Louis = new City("St. Louis");
        City Salt_Lake = new City("Salt Lake City");
        City San_Fransisco = new City("San Fransisco");
        City Santa_Fe = new City("Santa Fe");
        City Sault_St_Marie = new City("Sault St. Marie");
        City Seattle = new City("Seattle");
        City Toronto = new City("Toronto");
        City Vancouver = new City("Vancouver");
        City Washington_DC = new City("Washington DC");
        City Winnipeg = new City("Winnipeg");

        // Note: Naming convention for the paths--whichever is alphabetically
        //  first is the first in the name & pair
        Path seattle_van = new Path(COLORLESS, 1, Seattle, Vancouver); // NEEDS SECOND PATH
        Path calg_van = new Path(COLORLESS, 3, Calgary, Vancouver);

        // VANCOUVER
        cityPaths.add(seattle_van);
        cityPaths.add(calg_van);
        Vancouver.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path calg_seattle = new Path(COLORLESS, 4, Calgary, Seattle);
        Path helena_seattle = new Path(YELLOW, 6, Helena, Seattle);
        Path port_seattle = new Path(COLORLESS, 1, Portland, Seattle);  // NEEDS SECOND PATH

        // SEATTLE
        cityPaths.add(seattle_van);
        cityPaths.add(calg_seattle);
        cityPaths.add(helena_seattle);
        Seattle.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path port_slc = new Path(BLUE, 6, Portland, Salt_Lake);
        Path port_sanfran = new Path(PURPLE, 5, Portland, San_Fransisco);   // NEEDS SECOND PATH

        // PORTLAND
        cityPaths.add(port_sanfran);
        cityPaths.add(port_seattle);
        cityPaths.add(port_slc);
        Portland.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path sanfran_slc = new Path(ORANGE, 5, San_Fransisco, Salt_Lake); // NEEDS SECOND PATH
        Path la_sanfran = new Path(YELLOW, 3, Los_Angeles, San_Fransisco); // NEEDS SECOND PATH

        // SAN FRANSISCO
        cityPaths.add(sanfran_slc);
        cityPaths.add(la_sanfran);
        cityPaths.add(port_sanfran);
        San_Fransisco.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path lasvegas_la = new Path(COLORLESS, 2, Las_Vegas, Los_Angeles);
        Path la_phoenix = new Path(COLORLESS, 3, Los_Angeles, Phoenix);
        Path elpaso_la = new Path(BLACK, 6, El_Paso, Los_Angeles);

        // LOS ANGELES
        cityPaths.add(la_phoenix);
        cityPaths.add(la_sanfran);
        cityPaths.add(lasvegas_la);
        cityPaths.add(elpaso_la);
        Los_Angeles.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path calg_helena = new Path(COLORLESS, 4, Calgary, Helena);
        Path calg_winn = new Path(WHITE, 6, Calgary, Winnipeg);

        // CALGARY
        cityPaths.add(calg_helena);
        cityPaths.add(calg_seattle);
        cityPaths.add(calg_van);
        cityPaths.add(calg_winn);
        Calgary.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path helena_slc = new Path(PURPLE, 3, Helena, Salt_Lake);
        Path lasvegas_slc = new Path(ORANGE, 3, Las_Vegas, Salt_Lake);
        Path denver_slc = new Path(RED, 3, Denver, Salt_Lake); // NEEDS SECOND PATH

        // SALT LAKE CITY
        cityPaths.add(denver_slc);
        cityPaths.add(helena_slc);
        cityPaths.add(lasvegas_slc);
        cityPaths.add(port_slc);
        cityPaths.add(sanfran_slc);
        Salt_Lake.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path denver_phoenix = new Path(WHITE, 5, Denver, Phoenix);
        Path elpaso_phoenix = new Path(COLORLESS, 3, El_Paso, Phoenix);
        Path phoenix_santafe = new Path(COLORLESS, 3, Phoenix, Santa_Fe);

        // PHOENIX
        cityPaths.add(phoenix_santafe);
        cityPaths.add(denver_phoenix);
        cityPaths.add(elpaso_phoenix);
        cityPaths.add(la_phoenix);
        Phoenix.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path helena_winnipeg = new Path(BLUE, 4, Helena, Winnipeg);
        Path duluth_helena = new Path(ORANGE, 6, Duluth, Helena);
        Path helena_omaha = new Path(RED, 5, Helena, Omaha);
        Path helena_denver = new Path(GREEN, 4, Helena, Denver);

        // HELENA
        cityPaths.add(helena_denver);
        cityPaths.add(helena_omaha);
        cityPaths.add(helena_seattle);
        cityPaths.add(helena_slc);
        cityPaths.add(helena_winnipeg);
        cityPaths.add(calg_helena);
        cityPaths.add(duluth_helena);
        Helena.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path denver_omaha = new Path(PURPLE, 4, Denver, Omaha);
        Path denver_kansas = new Path(BLACK, 4, Denver, Kansas_City); // NEEDS SECOND PATH
        Path denver_oklahoma = new Path(RED, 4, Denver, Oklahoma_City);
        Path denver_santafe = new Path(COLORLESS, 2, Denver, Santa_Fe);

        // DENVER
        cityPaths.add(denver_kansas);
        cityPaths.add(denver_oklahoma);
        cityPaths.add(denver_omaha);
        cityPaths.add(denver_phoenix);
        cityPaths.add(denver_santafe);
        cityPaths.add(denver_slc);
        cityPaths.add(helena_denver);
        Denver.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path oklahoma_santafe = new Path(BLUE, 3, Oklahoma_City, Santa_Fe);
        Path elpaso_santafe = new Path(COLORLESS, 2, El_Paso, Santa_Fe);

        // SANTA FE
        cityPaths.add(denver_santafe);
        cityPaths.add(elpaso_santafe);
        cityPaths.add(oklahoma_santafe);
        cityPaths.add(phoenix_santafe);
        Santa_Fe.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path elpaso_oklahoma = new Path(YELLOW, 5, El_Paso, Oklahoma_City);
        Path dallas_elpaso = new Path(RED, 4, Dallas, El_Paso);
        Path elpaso_houston = new Path(GREEN, 6, El_Paso, Houston);

        // EL PASO
        cityPaths.add(elpaso_houston);
        cityPaths.add(elpaso_la);
        cityPaths.add(elpaso_oklahoma);
        cityPaths.add(elpaso_phoenix);
        cityPaths.add(elpaso_santafe);
        cityPaths.add(dallas_elpaso);
        El_Paso.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path duluth_winnapeg = new Path(BLACK, 4, Duluth, Winnipeg);
        Path stMarie_winnapeg = new Path(COLORLESS, 6, Sault_St_Marie, Winnipeg);

        // WINNAPEG
        cityPaths.add(calg_winn);
        cityPaths.add(duluth_winnapeg);
        cityPaths.add(helena_winnipeg);
        cityPaths.add(stMarie_winnapeg);
        Winnipeg.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path duluth_stMarie = new Path(COLORLESS, 3, Duluth, Sault_St_Marie);
        Path duluth_toronto = new Path(PURPLE, 6, Duluth, Toronto);
        Path chicago_duluth = new Path(RED, 3, Chicago, Duluth);
        Path duluth_omaha = new Path(COLORLESS, 2, Duluth, Omaha); // NEEDS SECOND PATH

        // DULUTH
        cityPaths.add(duluth_helena);
        cityPaths.add(duluth_omaha);
        cityPaths.add(duluth_stMarie);
        cityPaths.add(duluth_toronto);
        cityPaths.add(duluth_winnapeg);
        cityPaths.add(chicago_duluth);
        Duluth.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path chicago_omaha = new Path(BLUE, 4, Chicago, Omaha);
        Path kansas_omaha = new Path(COLORLESS, 1, Kansas_City, Omaha); // NEEDS SECOND PATH

        // OMAHA
        cityPaths.add(chicago_omaha);
        cityPaths.add(denver_omaha);
        cityPaths.add(duluth_omaha);
        cityPaths.add(helena_omaha);
        cityPaths.add(kansas_omaha);
        Omaha.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path kansas_stlouis = new Path(BLUE, 2, Kansas_City, St_Louis); // NEEDS SECOND PATH
        Path kansas_oklahoma = new Path(COLORLESS, 2, Kansas_City, Oklahoma_City); // NEEDS SECOND PATH

        // KANSAS CITY
        cityPaths.add(kansas_oklahoma);
        cityPaths.add(kansas_omaha);
        cityPaths.add(kansas_stlouis);
        cityPaths.add(denver_kansas);
        Kansas_City.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path dallas_oklahoma = new Path(COLORLESS, 2, Dallas, Oklahoma_City); // NEEDS SECOND PATH
        Path littlerock_oklahoma = new Path(COLORLESS, 2, Little_Rock, Oklahoma_City);

        // OKLAHOMA CITY
        cityPaths.add(oklahoma_santafe);
        cityPaths.add(dallas_oklahoma);
        cityPaths.add(denver_oklahoma);
        cityPaths.add(elpaso_oklahoma);
        cityPaths.add(kansas_oklahoma);
        cityPaths.add(littlerock_oklahoma);
        Oklahoma_City.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path dallas_littlerock = new Path(COLORLESS, 2, Dallas, Little_Rock);
        Path dallas_houston = new Path(COLORLESS, 1, Dallas, Houston); // NEEDS SECOND PATH

        // DALLAS
        cityPaths.add(dallas_elpaso);
        cityPaths.add(dallas_houston);
        cityPaths.add(dallas_littlerock);
        cityPaths.add(dallas_oklahoma);
        Dallas.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path houston_neworleans = new Path(COLORLESS, 2, Houston, New_Orleans);

        // HOUSTON
        cityPaths.add(houston_neworleans);
        cityPaths.add(dallas_houston);
        cityPaths.add(elpaso_houston);
        Houston.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path stMarie_toronto = new Path(COLORLESS, 2, Sault_St_Marie, Toronto);
        Path montreal_stMarie = new Path(BLACK, 5, Montreal, Sault_St_Marie);

        // SAULT ST. MARIE
        cityPaths.add(stMarie_toronto);
        cityPaths.add(stMarie_winnapeg);
        cityPaths.add(duluth_stMarie);
        cityPaths.add(montreal_stMarie);
        Sault_St_Marie.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path chicago_stlouis = new Path(WHITE, 2, Chicago, St_Louis); // NEEDS SECOND PATH
        Path chicago_pitts = new Path(ORANGE, 3, Chicago, Pittsburgh); // NEEDS SECOND PATH
        Path chicago_toronto = new Path(WHITE, 4, Chicago, Toronto);

        // CHICAGO
        cityPaths.add(chicago_duluth);
        cityPaths.add(chicago_omaha);
        cityPaths.add(chicago_pitts);
        cityPaths.add(chicago_stlouis);
        cityPaths.add(chicago_toronto);
        Chicago.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path pitts_stlouis = new Path(GREEN, 5, Pittsburgh, St_Louis);
        Path nashville_stlouis = new Path(COLORLESS, 2, Nashville, St_Louis);
        Path littlerock_stlouis = new Path(COLORLESS, 2, Little_Rock, St_Louis);

        // ST. LOUIS
        cityPaths.add(chicago_stlouis);
        cityPaths.add(kansas_stlouis);
        cityPaths.add(littlerock_stlouis);
        cityPaths.add(nashville_stlouis);
        cityPaths.add(pitts_stlouis);
        St_Louis.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path littlerock_nashville = new Path(WHITE, 3, Little_Rock, Nashville);
        Path littlerock_neworleans = new Path(GREEN, 3, Little_Rock, New_Orleans);

        // LITTLE ROCK
        cityPaths.add(littlerock_nashville);
        cityPaths.add(littlerock_neworleans);
        cityPaths.add(littlerock_oklahoma);
        cityPaths.add(littlerock_stlouis);
        cityPaths.add(dallas_littlerock);
        Little_Rock.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path miami_neworleans = new Path(RED, 6, Miami, New_Orleans);
        Path atlanta_neworleans = new Path(YELLOW, 4, Atlanta, New_Orleans); // NEEDS SECOND PATH

        // NEW ORLEANS
        cityPaths.add(atlanta_neworleans);
        cityPaths.add(houston_neworleans);
        cityPaths.add(littlerock_neworleans);
        cityPaths.add(miami_neworleans);
        New_Orleans.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path montreal_toronto = new Path(COLORLESS, 3, Montreal, Toronto);
        Path pitts_toronto = new Path(COLORLESS, 2, Pittsburgh, Toronto);

        // TORONTO
        cityPaths.add(chicago_toronto);
        cityPaths.add(duluth_toronto);
        cityPaths.add(montreal_toronto);
        cityPaths.add(pitts_toronto);
        cityPaths.add(stMarie_toronto);
        Toronto.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path newyork_pitts = new Path(WHITE, 2, New_York, Pittsburgh); // NEEDS SECOND PATH
        Path pitts_washington = new Path(COLORLESS, 2, Pittsburgh, Washington_DC);
        Path pitts_raleigh = new Path(COLORLESS, 2, Pittsburgh, Raleigh);
        Path nashville_pitts = new Path(YELLOW, 4, Nashville, Pittsburgh);

        // PITTSBURGH
        cityPaths.add(pitts_raleigh);
        cityPaths.add(newyork_pitts);
        cityPaths.add(nashville_pitts);
        cityPaths.add(chicago_pitts);
        cityPaths.add(pitts_stlouis);
        cityPaths.add(pitts_toronto);
        cityPaths.add(pitts_washington);
        Pittsburgh.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path atlanta_nashville = new Path(COLORLESS, 1, Atlanta, Nashville);
        Path nashville_raleigh = new Path(BLACK, 3, Nashville, Raleigh);

        // NASHVILLE
        cityPaths.add(nashville_pitts);
        cityPaths.add(nashville_raleigh);
        cityPaths.add(nashville_stlouis);
        cityPaths.add(atlanta_nashville);
        cityPaths.add(littlerock_nashville);
        Nashville.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path atlanta_raleigh = new Path(COLORLESS, 2, Atlanta, Raleigh); // NEEDS SECOND PATH
        Path atlanta_charleston = new Path(COLORLESS, 2, Atlanta, Charleston);
        Path atlanta_miami = new Path(BLUE, 5, Atlanta, Miami);

        // ATLANTA
        cityPaths.add(atlanta_charleston);
        cityPaths.add(atlanta_miami);
        cityPaths.add(atlanta_nashville);
        cityPaths.add(atlanta_neworleans);
        cityPaths.add(atlanta_raleigh);
        Atlanta.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path boston_montreal = new Path(COLORLESS, 2, Boston, Montreal); // NEEDS SECOND PATH
        Path montreal_newyork = new Path(BLUE, 3, Montreal, New_York);

        // MONTREAL
        cityPaths.add(montreal_newyork);
        cityPaths.add(montreal_stMarie);
        cityPaths.add(montreal_toronto);
        cityPaths.add(boston_montreal);
        Montreal.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path boston_newyork = new Path(RED, 2, Boston, New_York); // NEEDS SECOND PATH

        // BOSTON
        cityPaths.add(boston_montreal);
        cityPaths.add(boston_newyork);
        Boston.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path newyork_washington = new Path(ORANGE, 2, New_York, Washington_DC); // NEEDS SECOND PATH

        // NEW YORK
        cityPaths.add(newyork_pitts);
        cityPaths.add(newyork_washington);
        cityPaths.add(boston_newyork);
        cityPaths.add(montreal_newyork);
        New_York.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path raleigh_washington = new Path(COLORLESS, 2, Raleigh, Washington_DC); // NEEDS SECOND PATH

        // WASHINGTON DC
        cityPaths.add(newyork_washington);
        cityPaths.add(raleigh_washington);
        cityPaths.add(pitts_washington);
        Washington_DC.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path charleston_raleigh = new Path(COLORLESS, 2, Charleston, Raleigh);

        // RALEIGH
        cityPaths.add(raleigh_washington);
        cityPaths.add(atlanta_raleigh);
        cityPaths.add(charleston_raleigh);
        cityPaths.add(nashville_raleigh);
        cityPaths.add(pitts_raleigh);
        Raleigh.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path charleston_miami = new Path(PURPLE, 4, Charleston, Miami);

        // CHARLESTON
        cityPaths.add(charleston_miami);
        cityPaths.add(charleston_raleigh);
        cityPaths.add(atlanta_charleston);
        Charleston.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        // MIAMI
        cityPaths.add(miami_neworleans);
        cityPaths.add(atlanta_miami);
        cityPaths.add(charleston_miami);
        Miami.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        // Init map of cities
        cities.put(Atlanta.getCityName(), Atlanta);
        cities.put(Boston.getCityName(), Boston);
        cities.put(Calgary.getCityName(), Calgary);
        cities.put(Charleston.getCityName(), Charleston);
        cities.put(Chicago.getCityName(), Chicago);
        cities.put(Dallas.getCityName(), Dallas);
        cities.put(Denver.getCityName(), Denver);
        cities.put(Duluth.getCityName(), Duluth);
        cities.put(El_Paso.getCityName(), El_Paso);
        cities.put(Helena.getCityName(), Helena);
        cities.put(Houston.getCityName(), Houston);
        cities.put(Kansas_City.getCityName(), Kansas_City);
        cities.put(Las_Vegas.getCityName(), Las_Vegas);
        cities.put(Little_Rock.getCityName(), Little_Rock);
        cities.put(Los_Angeles.getCityName(), Los_Angeles);
        cities.put(Miami.getCityName(), Miami);
        cities.put(Montreal.getCityName(), Montreal);
        cities.put(Nashville.getCityName(), Nashville);
        cities.put(New_Orleans.getCityName(), New_Orleans);
        cities.put(New_York.getCityName(), New_York);
        cities.put(Oklahoma_City.getCityName(), Oklahoma_City);
        cities.put(Omaha.getCityName(), Omaha);
        cities.put(Phoenix.getCityName(), Phoenix);
        cities.put(Pittsburgh.getCityName(), Pittsburgh);
        cities.put(Portland.getCityName(), Portland);
        cities.put(Raleigh.getCityName(), Raleigh);
        cities.put(St_Louis.getCityName(), St_Louis);
        cities.put(Salt_Lake.getCityName(), Salt_Lake);
        cities.put(San_Fransisco.getCityName(), San_Fransisco);
        cities.put(Santa_Fe.getCityName(), Santa_Fe);
        cities.put(Sault_St_Marie.getCityName(), Sault_St_Marie);
        cities.put(Seattle.getCityName(), Seattle);
        cities.put(Toronto.getCityName(), Toronto);
        cities.put(Vancouver.getCityName(), Vancouver);
        cities.put(Washington_DC.getCityName(), Washington_DC);
        cities.put(Winnipeg.getCityName(), Winnipeg);

        ClientModel.SINGLETON.setCitiesList(cities);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof City)) return false;

        City city = (City) obj;
        if (!city.mPaths.equals(this.mPaths)) return false;

        return super.equals(obj);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String name = mCityName.toUpperCase() + " | Connected Cities:";
        sb.append(name);

        for (Path path : mPaths)
        {
            sb.append("\n");
            Pair<City, City> pair = path.getCities();

            if (pair.first.equals(this))
                sb.append(pair.second.mCityName);
            else
                sb.append(pair.first.mCityName);
        }

        return sb.toString();
    }
}
