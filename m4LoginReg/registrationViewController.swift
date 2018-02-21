//
//  registrationViewController.swift
//  m4LoginReg
//
//  Created by Krrish Dholakia on 2/8/18.
//  Copyright © 2018 Krrish Dholakia. All rights reserved.
//

import Foundation
import UIKit
class registrationViewController: UIViewController{
    
    @IBOutlet weak var userNameTxtField: UITextField!
    @IBOutlet weak var pwdTxtField: UITextField!
    @IBOutlet weak var genderTxtField: UITextField!
    @IBOutlet weak var veteranTxtField: UITextField!
 
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        
    }
    
    @IBAction func onNextClicked(_ sender: UIButton) {
        if !userNameTxtField.text!.isEmpty &&
            !pwdTxtField.text!.isEmpty &&
            !genderTxtField.text!.isEmpty &&
            !veteranTxtField.text!.isEmpty {
            performSegue(withIdentifier: "registrationIdentifier", sender: self)
        }
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let destination = segue.destination as? registrationSecondFile {
            destination.userName = userNameTxtField.text
            destination.password = pwdTxtField.text
            destination.gender = genderTxtField.text
            destination.veteran = veteranTxtField.text
        }
    }
}

