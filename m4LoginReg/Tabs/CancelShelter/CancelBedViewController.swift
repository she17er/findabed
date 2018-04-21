//
//  CancelBedViewController.swift
//  m4LoginReg
//
//  Created by Krrish Dholakia on 4/9/18.
//  Copyright © 2018 Krrish Dholakia. All rights reserved.
//

import UIKit
import Alamofire

class CancelBedViewController: UIViewController {

    var id: String = ""
    var currCapacity: Int = 0
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func onYesClicked(_ sender: Any) {
        let beds = ShelterPersistence.getBookedBeds()
        let parameters: Parameters = [
            
            "currCapacity": currCapacity - beds
        ]
        ShelterPersistence.toggleBookedShelter(id)
        Alamofire.request("https://she17er.herokuapp.com/api/shelter/updateCapacity/\(id)", method: .post, parameters: parameters, encoding: JSONEncoding.default, headers: nil).responseString {
            response in
            print (response)
        }
        ShelterPersistence.toggleBookedBeds(beds)
    }
    
    @IBAction func onNoClicked(_ sender: Any) {
        self.navigationController?.popViewController(animated: true)
    }
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
