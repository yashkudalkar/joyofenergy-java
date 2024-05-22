# joyofenergy-java

As an electricity consumer, I want to be able to view my usage cost of the last week so that I can monitor my spending

Acceptance Criteria:

- Given I have a smart meter ID with price plan attached to it and usage data stored, when I request the usage cost then I am shown the correct cost of last week's usage
- Given I have a smart meter ID without a price plan attached to it and usage data stored, when I request the usage cost then an error message is displayed

How to calculate usage cost

- Unit of meter readings : kW (KilloWatt)
- Unit of Time : Hour (h)
- Unit of Energy Consumed : kW x Hour = kWh
- Unit of Tariff : $ per kWh (ex 0.2 $ per kWh)

To calculate the usage cost for a duration (D) in which lets assume we have captured N electricity readings (er1,er2,er3....erN)

Average reading in KW = (er1.reading + er2.reading + ..... erN.Reading)/N
Usage time in hours = Duration(D) in hours
Energy consumed in kWh = average reading x usage time
Cost = tariff unit prices x energy consumed