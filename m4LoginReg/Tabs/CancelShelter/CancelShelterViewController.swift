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
    
    var backBtn: UIButton!
    
    var currCapacity:String = ""
    var phoneNumber:String = ""
    var acceptedTypes:String = ""
    var shelterName:String = ""
    var latitude:Double = 0
    var longitude:Double = 0
    var id:String = ""
    override func viewDidLoad() {
        
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let destination = segue.destination as? BedBookViewController {
            destination.id = self.id
            destination.currCapacity = Int(self.currCapacity)!
        }
    }
    
    @IBAction func cancelShelterBookingBtn(_ sender: Any) {
    }
    @IBAction func favouriteShelterBtn(_ sender: Any) {
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
