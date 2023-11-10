"use client";

import {
  GoogleMap,
  InfoWindowF,
  MarkerF,
  useJsApiLoader,
} from "@react-google-maps/api";

import { useEffect, useState } from "react";
import "dotenv/config";

const containerStyle = {
  width: "100%",
  height: "100%",
};

export type Place = {
  name: string;
  address: string;
  latitude: number;
  longitude: number;
};

interface GoogleMapComponentProps {
  places: Place[];
}

export function GoogleMapComponent() {
  const [selectedPlace, setSelectedPlace] = useState<Place | undefined>(
    undefined
  );
  const [places, setPlaces] = useState<Place[]>([
    {
      name: "Warszawa",
      address: "Aleje Jerozolimskie 52",
      latitude: 52.22977,
      longitude: 21.01178,
    },
  ]);
  const [position, setPosition] = useState({
    lat: places[0].latitude,
    lng: places[0].longitude,
  });
  const { isLoaded } = useJsApiLoader({
    googleMapsApiKey: process.env.NEXT_PUBLIC_GOOGLE_APPS_API_KEY!,
  });

  return (
    <>
      {isLoaded && position.lat && position.lng && (
        <GoogleMap
          mapContainerStyle={containerStyle}
          center={{ lat: position.lat, lng: position.lng }}
          zoom={14}
        >
          {places.map((place) => (
            <MarkerF
              key={`${place.address}-${place.name}-${place.latitude}-${place.longitude}`}
              onClick={() => {
                place === selectedPlace
                  ? setSelectedPlace(undefined)
                  : setSelectedPlace(place);
              }}
              position={{ lat: place.latitude, lng: place.longitude }}
            />
          ))}
          {selectedPlace && (
            <InfoWindowF
              position={{
                lat: selectedPlace.latitude,
                lng: selectedPlace.longitude,
              }}
              zIndex={1}
              options={{ pixelOffset: new google.maps.Size(0, -40) }}
              onCloseClick={() => setSelectedPlace(undefined)}
            >
              <div className="blackFont">
                <h5>{selectedPlace.name}</h5>
                <span>{selectedPlace.address}</span>
              </div>
            </InfoWindowF>
          )}
        </GoogleMap>
      )}
    </>
  );
}
