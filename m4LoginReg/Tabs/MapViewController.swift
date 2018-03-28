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

class MapViewController: UIViewController {

    @IBOutlet weak var mapView: MKMapView!
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    var coOrdinates:[CoOrdinates] = []
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        
        requestCoOrdinates()
        
    }
    
    func requestCoOrdinates() {
        Alamofire.request("https://she17er.herokuapp.com/api/shelter/getShelters").validate().responseData {
            response in
            
            guard let data = response.data else {
                /* handle error? */ return
            }
            
            self.coOrdinates = (try?
                JSONDecoder().decode([CoOrdinates].self, from: data)) ?? []
            
            self.loadCoOrdinates()

        }
        
    }
    
    func loadCoOrdinates() {
        for coOrdinate in coOrdinates {
             var coOrdinator = coOrdinate.coOrdinates.components(separatedBy: ",")
            self.latitude = Double(coOrdinator[1])!
            self.longitude = Double(coOrdinator[0])!
            let myAnnotation: MKPointAnnotation = MKPointAnnotation()
            myAnnotation.coordinate = CLLocationCoordinate2DMake(latitude, longitude)
            myAnnotation.title = coOrdinate.name
            mapView.addAnnotation(myAnnotation)
            
        }
    }
    
    

}
