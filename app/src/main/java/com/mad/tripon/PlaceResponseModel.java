package com.mad.tripon;

import java.util.ArrayList;

public class PlaceResponseModel {
    public ArrayList<Object> html_attributions;
    public ArrayList<Result> results;
    public String status;
    public class Result{
        public String formatted_address;
        public Geometry geometry;
        public String icon;
        public String icon_background_color;
        public String icon_mask_base_uri;
        public String name;
        public String place_id;
        public String reference;
        public ArrayList<String> types;

        public String getFormatted_address() {
            return formatted_address;
        }

        public String getIcon() {
            return icon;
        }

        public String getIcon_background_color() {
            return icon_background_color;
        }

        public String getIcon_mask_base_uri() {
            return icon_mask_base_uri;
        }

        public String getName() {
            return name;
        }

        public String getPlace_id() {
            return place_id;
        }

        public String getReference() {
            return reference;
        }

        public Geometry getGeometry() {
            return geometry;
        }

        public class Geometry{
            public Location location;

            public class Location{
                public double lat;
                public double lng;
            }

        }
    }
}
