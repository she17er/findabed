//
//  shelterStruct.swift
//  m4LoginReg
//
//  Created by Krrish Dholakia on 3/27/18.
//  Copyright © 2018 Krrish Dholakia. All rights reserved.
//

import Foundation

class Shelter: NSObject, Codable, NSCoding {
    
    let name: String
    let acceptedTypes: [String]
    let phoneNumber: Int
    let currCapacity: Int
    let coOrdinates: String
    let _id: String
    
    init(name: String, acceptedTypes:String, phoneNumber: Int, currCapacity: Int, coOrdinates: String, _id: String) {
        self.name = name
        self.acceptedTypes = [acceptedTypes]
        self.phoneNumber = phoneNumber
        self.currCapacity = currCapacity
        self.coOrdinates = coOrdinates
        self._id = _id 
    }
    
    required init?(coder aDecoder: NSCoder) {
        self.name = aDecoder.decodeObject(forKey: "name") as! String!;
        self.currCapacity = aDecoder.decodeObject(forKey: "currCapacity") as! Int;
        self.phoneNumber = aDecoder.decodeObject(forKey: "phoneNumber") as! Int;
        self.acceptedTypes = aDecoder.decodeObject(forKey: "acceptedTypes") as! [String];
        self.coOrdinates = aDecoder.decodeObject(forKey: "coOrdinates") as! String!;
        self._id = aDecoder.decodeObject(forKey: "_id") as! String;
    }
    
    func encode(with aCoder: NSCoder) {
        aCoder.encode(self.name, forKey: "name");
        aCoder.encode(self.currCapacity, forKey: "currCapacity");
        aCoder.encode(self.phoneNumber, forKey: "phoneNumber");
        aCoder.encode(self.acceptedTypes, forKey: "acceptedTypes");
        aCoder.encode(self.coOrdinates, forKey: "coOrdinates");
        aCoder.encode(self._id, forKey: "_id");

    }
}
