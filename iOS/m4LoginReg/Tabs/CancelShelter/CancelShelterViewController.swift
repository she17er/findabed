//
//  CancelShelterViewController.swift
//  m4LoginReg
//
//  Created by Krrish Dholakia on 4/9/18.
//  Copyright © 2018 Krrish Dholakia. All rights reserved.
//

import UIKit
import MapKit

class CancelShelterViewController: UIViewController {

    @IBOutlet weak var mapView: MKMapView!
    @IBOutlet weak var currentCapacityUI: UITextView!
    @IBOutlet weak var shelterNameUI: UILabel!
    @IBOutlet weak var acceptedTypesUI: UITextView!
    @IBOutlet weak var phoneNumberUI: UITextView!
    @IBOutlet weak var starBtn: UIButton!
    
    var backBtn: UIButton!
    
    var currCapacity:String = ""
    var phoneNumber:String = ""
    var acceptedTypes:String = ""
    var shelterName:String = ""
    var latitude:Double = 0
    var longitude:Double = 0
    var id:String = ""
    override func viewDidLoad() {
        backBtn = makeCancelButton(xVal: 16, yVal: 28)
        
        shelterNameUI.text = shelterName
        acceptedTypesUI.text = "ACCEPTED TYPES • \(acceptedTypes)"
        phoneNumberUI.text = "Phone Number \n\(phoneNumber)"
        currentCapacityUI.text = "Current Capacity | \(currCapacity)"
        
        mapView.centerCoordinate = CLLocationCoordinate2D(latitude: latitude, longitude: longitude)
        
        let myAnnotation: MKPointAnnotation = MKPointAnnotation()
        myAnnotation.coordinate = CLLocationCoordinate2DMake(latitude, longitude)
        myAnnotation.title = "Shelter"
        mapView.addAnnotation(myAnnotation)
        
        backBtn.addTarget(self, action: #selector(self.GoBack), for: .touchUpInside)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let destination = segue.destination as? CancelBedViewController {
            destination.id = self.id
            destination.currCapacity = Int(self.currCapacity)!
        }
    }
    
    @IBAction func cancelShelterBookingBtn(_ sender: Any) {
        performSegue(withIdentifier: "cancelBedIdentifier", sender: self)
    }
    @IBAction func favouriteShelterBtn(_ sender: Any) {
        ShelterPersistence.toggleFavouriteShelterById(self.id)
        
        starBtn.setTitle("★", for: .normal)
    }
    @objc func GoBack() {
        self.navigationController?.popViewController(animated: true)
    }
    
    func makeCancelButton(xVal: Int, yVal: Int) -> UIButton {
        let button = UIButton(type: .custom)
        button.frame = CGRect(x: xVal, y: yVal, width: 30, height: 30)
        button.clipsToBounds = true
        button.backgroundColor = .clear
        button.setTitleColor(UIColor.black, for: .normal)
        button.setTitle("╳", for: .normal)
        button.titleLabel?.font = UIFont.boldSystemFont(ofSize: 23)
        view.addSubview(button)
        return button
    }
    

}
