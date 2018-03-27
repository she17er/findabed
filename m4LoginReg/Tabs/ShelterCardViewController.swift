//
//  ShelterCardViewController.swift
//  m4LoginReg
//
//  Created by Krrish Dholakia on 3/26/18.
//  Copyright © 2018 Krrish Dholakia. All rights reserved.
//

import UIKit
import Alamofire
import SwiftyJSON


class ShelterCardViewController: UIViewController, UICollectionViewDelegate, UICollectionViewDataSource {

    var shelterNames:[String] = []
    var shelterAcceptedTypes:[String] = []
    var shelterPhoneNumbers:[String] = []
    var shelterCurrentCapacity:[String] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        
        Alamofire.request("https://she17er.herokuapp.com/api/shelter/getShelters").validate().responseJSON {
            (responseObject) -> Void in
            
//            print(responseObject)
            
            if responseObject.result.isSuccess {
                let resJson = JSON(responseObject.result.value!)
                print(resJson[0])
                
                for (key,subJson):(String, JSON) in resJson {
                    // Do something you want
                    self.shelterNames.append(subJson["name"].stringValue)
                
                    self.shelterAcceptedTypes.append(subJson["acceptedAge"].stringValue)
                    self.shelterCurrentCapacity.append(subJson["currCapacity"].stringValue)
                    self.shelterPhoneNumbers.append(subJson["phoneNumber"].stringValue)
                    print(self.shelterNames)
                    
                }
            }
            if responseObject.result.isFailure {
                print("error")
            }
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return 3
    }
    
    
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "cell", for: indexPath) as! CollectionViewCell
        
        print(indexPath.row)
        cell.labelName.text = shelterNames[indexPath.row]
        cell.acceptedTypesLabel.text = shelterAcceptedTypes[indexPath.row]
        cell.phoneNumberLabel.text = shelterPhoneNumbers[indexPath.row]
        cell.currCapacityLabel.text = shelterCurrentCapacity[indexPath.row]
        //This creates the shadows and modifies the cards a little bit
//        cell.contentView.layer.cornerRadius = 4.0
//        cell.contentView.layer.borderWidth = 1.0
//        cell.contentView.layer.borderColor = UIColor.clear.cgColor
//        cell.contentView.layer.masksToBounds = false
//        cell.layer.shadowColor = UIColor.gray.cgColor
//        cell.layer.shadowOffset = CGSize(width: 0, height: 1.0)
//        cell.layer.shadowRadius = 4.0
//        cell.layer.shadowOpacity = 1.0
//        cell.layer.masksToBounds = false
//        cell.layer.shadowPath = UIBezierPath(roundedRect: cell.bounds, cornerRadius: cell.contentView.layer.cornerRadius).cgPath
//
        
        return cell
    }

    
    
}
