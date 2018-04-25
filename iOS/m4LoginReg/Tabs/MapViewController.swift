//
//  MapViewController.swift
//  m4LoginReg
//
//  Created by Krrish Dholakia on 3/26/18.
//  Copyright © 2018 Krrish Dholakia. All rights reserved.
//

import UIKit
import MapKit
import Alamofire
import SwiftyJSON

class MapViewController: UIViewController, MKMapViewDelegate, CLLocationManagerDelegate{

    @IBOutlet weak var mapView: MKMapView!
    var currCapacity:String = ""
    var phoneNumber:String = ""
    var acceptedTypes:[String] = []
    var shelterName:String = ""
    var latitudeClick:Double = 0
    var longitudeClick:Double = 0
    var id:String = ""
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    var shelters:[Shelter] = []
    
    
    private var locationManager: CLLocationManager!
    private var currentLocation: CLLocation?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        mapView.delegate = self
        
        requestCoOrdinates()
        
        locationManager = CLLocationManager()
        locationManager.delegate = self
        locationManager.desiredAccuracy = kCLLocationAccuracyBest
        
        // Check for Location Services
        
        if CLLocationManager.locationServicesEnabled() {
            locationManager.requestWhenInUseAuthorization()
            locationManager.startUpdatingLocation()
        }
    }
    
    func requestCoOrdinates() {
        Alamofire.request("https://she17er.herokuapp.com/api/shelter/getShelters").validate().responseData {
            response in
            
            guard let data = response.data else {
                /* handle error? */ return
            }
            
            self.shelters = (try?
                JSONDecoder().decode([Shelter].self, from: data)) ?? []
            
            self.loadCoOrdinates()

        }
        
    }
    
    func loadCoOrdinates() {
        for shelter in shelters {
             var coOrdinator = shelter.coOrdinates.components(separatedBy: ",")
            self.latitude = Double(coOrdinator[1])!
            self.longitude = Double(coOrdinator[0])!
            let myAnnotation: MKPointAnnotation = MKPointAnnotation()
            myAnnotation.coordinate = CLLocationCoordinate2DMake(latitude, longitude)
            myAnnotation.title = shelter.name
            mapView.addAnnotation(myAnnotation)
            
        }
    }
    
    func mapView(_ mapView: MKMapView, didSelect view: MKAnnotationView) {
        
        if let annotationTitle = view.annotation?.title {
            for shelter in shelters {
                if shelter.name == annotationTitle {
                    
                    currCapacity = String(shelter.currCapacity)
                    phoneNumber = String(shelter.phoneNumber)
                    acceptedTypes = shelter.acceptedTypes
                    shelterName = shelter.name
                    var coOrdinates = shelter.coOrdinates.components(separatedBy: ",")
                    latitudeClick = Double(coOrdinates[1])!
                    longitudeClick = Double(coOrdinates[0])!
                    id = shelter._id
                    performSegue(withIdentifier: "bedBookingIdentifier", sender: self)
                    
                }
            }
        }
    }
    
    // MARK - CLLocationManagerDelegate
    
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        defer { currentLocation = locations.last }
        
        if currentLocation == nil {
            // Zoom to user location
            if let userLocation = locations.last {
                let viewRegion = MKCoordinateRegionMakeWithDistance(userLocation.coordinate, 2000, 2000)
                mapView.setRegion(viewRegion, animated: false)
            }
        }
    }
    
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let destination = segue.destination as? ShelterViewController {
            destination.currCapacity = currCapacity
            destination.phoneNumber = phoneNumber
            destination.acceptedTypes = acceptedTypes[0]
            destination.shelterName = shelterName
            destination.latitude = latitudeClick
            destination.longitude = longitudeClick
            destination.id = id
        }
    }
    
    
    
    
    

}
