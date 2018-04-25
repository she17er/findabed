//
//  shelterRegistrationContinuedViewController.swift
//  m4LoginReg
//
//  Created by Krrish Dholakia on 2/28/18.
//  Copyright © 2018 Krrish Dholakia. All rights reserved.
//

import Foundation
import UIKit
import GooglePlaces
import Alamofire

class shelterRegistrationContinuedViewController: UIViewController, UITextViewDelegate {
    var street_number: String = ""
    var route: String = ""
    var coOrdinates: String = ""
    
    var name: String?
    var currCapacity: String?
    var maxCapacity: String?
    var phoneNumber: String?
    
    @IBOutlet weak var address_line_1: UITextField!
    @IBOutlet weak var locationBtn: UIButton!

    @IBOutlet weak var specialNotesTxtField: UITextView!
    @IBAction func autocompleteClicked(_ sender: Any) {
        let autocompleteController = GMSAutocompleteViewController()
        autocompleteController.delegate = self
        
        // Set a filter to return only addresses.
        let addressFilter = GMSAutocompleteFilter()
        addressFilter.type = .address
        autocompleteController.autocompleteFilter = addressFilter
        
        present(autocompleteController, animated: true, completion: nil)
    }
    
    override func viewDidLoad() {
        self.specialNotesTxtField.delegate = self
    }
    @IBAction func onNotesClicked (_ sender: Any) {
        specialNotesTxtField.text = "";
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.specialNotesTxtField.resignFirstResponder()
    }
    func textViewDidBeginEditing(_ textView: UITextView) {
        specialNotesTxtField.text = ""
        
    }
    func fillAddressForm() {
        address_line_1.text = street_number + " " + route
        
        // Clear values for next time.
        street_number = ""
        route = ""
        
    }
    
    @IBAction func onDoneClicked(_ sender: Any) {
//        let parameters: Parameters = [
//            "name": name ?? "",
//            "coOrdinates": coOrdinates ?? "",
//            "location": street_number + " " + route ?? "",
//            "currCapacity": currCapacity ?? "",
//            "maxCapacity": maxCapacity ?? "",
//            "acceptedTypes": 
//            "role": roleTxt,
//            "account_State": "existslmao",
//            "login": "true"
//        ]
//        
//
//        Alamofire.request("https://she17er.herokuapp.com/api/users/newUsers", method: HTTPMethod.post, parameters: parameters, encoding: JSONEncoding.default, headers: nil).validate().responseString {
//            response in
//            print(response)
//
//        }
        
    }
}


extension shelterRegistrationContinuedViewController: GMSAutocompleteViewControllerDelegate {
    
    // Handle the user's selection.
    func viewController(_ viewController: GMSAutocompleteViewController, didAutocompleteWith place: GMSPlace) {
        
        coOrdinates = String(place.coordinate.latitude) + "," + String(place.coordinate.longitude)
        
        // Get the address components.
        if let addressLines = place.addressComponents {
            // Populate all of the address fields we can find.
            for field in addressLines {
                switch field.type {
                case kGMSPlaceTypeStreetNumber:
                    street_number = field.name
                case kGMSPlaceTypeRoute:
                    route = field.name
                // Print the items we aren't using.
                default:
                    print("Type: \(field.type), Name: \(field.name)")
                }
            }
        }
        
        // Call custom function to populate the address form.
        fillAddressForm()
        
        // Close the autocomplete widget.
        self.dismiss(animated: true, completion: nil)
    }
    
    func viewController(_ viewController: GMSAutocompleteViewController, didFailAutocompleteWithError error: Error) {
        // TODO: handle the error.
        print("Error: ", error.localizedDescription)
    }
    
    func wasCancelled(_ viewController: GMSAutocompleteViewController) {
        dismiss(animated: true, completion: nil)
    }
    
    // Show the network activity indicator.
    func didRequestAutocompletePredictions(_ viewController: GMSAutocompleteViewController) {
        UIApplication.shared.isNetworkActivityIndicatorVisible = true
    }
    
    // Hide the network activity indicator.
    func didUpdateAutocompletePredictions(_ viewController: GMSAutocompleteViewController) {
        UIApplication.shared.isNetworkActivityIndicatorVisible = false
    }
    
}
