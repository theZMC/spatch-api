USE `spatch`;

INSERT INTO
  `technician` (
    `id`,
    `firstName`,
    `lastName`,
    `avatar`,
    `phone`,
    `email`
  )
VALUES
  (
    NULL,
    'Hawkeye',
    'Pierce',
    NULL,
    '5015551234',
    'hawkeye@spatch.app'
  ),
  (
    NULL,
    'BJ',
    'Hunnicutt',
    NULL,
    '5015552468',
    'bjhun@spatch.app'
  );

INSERT INTO
  `trip` (`id`, `date`, `technicianId`)
VALUES
  (NULL, '2022-02-17', '1'),
  (NULL, '2022-02-14', '2');

INSERT INTO
  `contact` (`id`, `firstName`, `lastName`, `phone`, `email`)
VALUES
  (
    NULL,
    'Margaret',
    'Houlihan',
    '5014441234',
    'margaret@mash4077.mil'
  ),
  (
    NULL,
    'Charles',
    'Winchester',
    '5014442468',
    'charwin@mash4077.mil'
  );

INSERT INTO
  `location` (
    `id`,
    `primaryContactId`,
    `address`,
    `gPlaceId`,
    `displayName`
  )
VALUES
  (
    NULL,
    '2',
    '6706 Cantrell Rd, Little Rock, AR 72207, USA',
    'ChIJC4aMj5mj0ocR6Fk2TgVdIsI',
    'Damgoode Pies'
  );

INSERT INTO
  `location` (
    `id`,
    `primaryContactId`,
    `address`,
    `gPlaceId`,
    `displayName`
  )
VALUES
  (
    NULL,
    '1',
    '7410 Cantrell Rd, Little Rock, AR 72207, USA',
    'ChIJ096Y2Oqj0ocRKekYd-GTGAY',
    'Hubcap Burger Company'
  );

INSERT INTO
  `dispatch` (
    `id`,
    `locationId`,
    `tripId`,
    `scheduledTime`,
    `startTime`,
    `endTime`,
    `estTimeOnSite`,
    `isComplete`,
    `priority`
  )
VALUES
  (
    1,
    1,
    1,
    '2022-02-14 13:00:00',
    NULL,
    NULL,
    1,
    0,
    'standard'
  );

INSERT INTO
  `note` (`id`, `dispatchId`, `time`, `message`)
VALUES
  (
    1,
    1,
    '2021-08-29 23:37:30',
    'Charles at Damgoode Pies states their secondary oven is down. Need service to diagnose and repair.'
  );