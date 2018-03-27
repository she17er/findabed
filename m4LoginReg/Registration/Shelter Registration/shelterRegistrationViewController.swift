//
//  File.swift
//  m4LoginReg
//
//  Created by Krrish Dholakia on 2/28/18.
//  Copyright © 2018 Krrish Dholakia. All rights reserved.
//

import Foundation
import UIKit

class shelterRegistrationViewController: UIViewController {
    // Declare variables to hold address form values.
    
    @IBOutlet weak var nameTxtField: UITextField!
    @IBOutlet weak var currCapacityTxtField: UITextField!
    @IBOutlet weak var maxCapacityTxtField: UITextField!
    @IBOutlet weak var phoneNumberTxtField: UITextField!
    @IBAction func onNextClicked(_ sender: Any) {
        if !nameTxtField.text!.isEmpty &&
            !currCapacityTxtField.text!.isEmpty &&
            !maxCapacityTxtField.text!.isEmpty {
            performSegue(withIdentifier: "shelterRegistrationSegue", sender: self)
        }
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let destination = segue.destination as? shelterRegistrationContinuedViewController {
            destination.name = nameTxtField.text
            destination.currCapacity = currCapacityTxtField.text
            destination.maxCapacity = maxCapacityTxtField.text
            destination.phoneNumber = phoneNumberTxtField.text
        }
    }
}



